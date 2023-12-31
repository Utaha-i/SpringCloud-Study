package com.utaha.kazusa.aspect;

import com.utaha.kazusa.annotation.RepeatSubmit;
import com.utaha.kazusa.constant.RedisKey;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 定义一个切面类
 **/
@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 定义 @Pointcut注解表达式, 通过特定的规则来筛选连接点, 就是Pointcut，选中那几个你想要的方法
     * 在程序中主要体现为书写切入点表达式（通过通配、正则表达式）过滤出特定的一组 JointPoint连接点
     * 方式一：@annotation：当执行的方法上拥有指定的注解时生效（本博客采用这）
     * 方式二：execution：一般用于指定方法的执行
     */
    @Pointcut("@annotation(repeatSubmit)")
    public void pointCutNoRepeatSubmit(RepeatSubmit repeatSubmit) {
    }

    /**
     * 环绕通知, 围绕着方法执行
     *
     * @param joinPoint    切人点
     * @param repeatSubmit 注解
     * @return Object
     * @throws Throwable 异常
     * @Around 可以用来在调用一个具体方法前和调用后来完成一些具体的任务。
     * <p>
     * 方式一：单用 @Around("execution(* net.wnn.controller.*.*(..))")可以
     * 方式二：用@Pointcut和@Around联合注解也可以（本博客采用这个）
     * <p>
     * <p>
     * 两种方式
     * 方式一：加锁 固定时间内不能重复提交
     * <p>
     * 方式二：先请求获取token，这边再删除token,删除成功则是第一次提交
     */
    @Around(value = "pointCutNoRepeatSubmit(repeatSubmit)", argNames = "joinPoint,repeatSubmit")
    public Object around(ProceedingJoinPoint joinPoint, RepeatSubmit repeatSubmit) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取登录账户
//        long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
        long accountNo = 18703442948L;
        //用于记录成功或者失败
        boolean res = false;
        //防重提交类型
        String type = repeatSubmit.limitType().name();
        if (type.equalsIgnoreCase(RepeatSubmit.Type.PARAM.name())) {
            //方式一，参数形式防重提交
            long lockTime = repeatSubmit.lockTime();
//            String ipAddr = CommonUtil.getIpAddr(request);
            String ipAddr = "127.0.0.1";
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            String className = method.getDeclaringClass().getName();
            String key = "order-server:repeat_submit:" + String.format("%s-%s-%s-%s", ipAddr, className, method, accountNo);
            //加锁
            // 这种也可以 本博客也介绍下redisson的使用
            // res  = redisTemplate.opsForValue().setIfAbsent(key, "1", lockTime, TimeUnit.SECONDS);
            RLock lock = redissonClient.getLock(key);
            // 尝试加锁，最多等待0秒，上锁以后5秒自动解锁 [lockTime默认为5s, 可以自定义]
            res = lock.tryLock(0, lockTime, TimeUnit.SECONDS);
        } else {
            //方式二，令牌形式防重提交
            String requestToken = request.getHeader("request-token");
            if (ObjectUtils.isEmpty(requestToken)) {
                throw new RuntimeException("Token为空");
            }
            String key = String.format(RedisKey.SUBMIT_ORDER_TOKEN_KEY, accountNo, requestToken);
            /*
              提交表单的token key
              方式一：不用lua脚本获取再判断，之前是因为 key组成是 order:submit:accountNo, value是对应的token，所以需要先获取值，再判断
              方式二：可以直接key是 order:submit:accountNo:token,然后直接删除成功则完成
             */
            res = redisTemplate.delete(key);
        }
        if (!res) {
            log.error("请求重复提交");
            log.info("环绕通知中");
            return null;
        }
        log.info("环绕通知执行前");
        Object obj = joinPoint.proceed();
        log.info("环绕通知执行后");
        return obj;
    }
}
