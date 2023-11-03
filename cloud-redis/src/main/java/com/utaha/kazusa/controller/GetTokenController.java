package com.utaha.kazusa.controller;

import com.utaha.kazusa.constant.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redis")
public class GetTokenController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 下单前获取令牌用于防重提交
     *
     * @return JsonData
     */
    @GetMapping("token")
    public String getOrderToken() {
        //获取登录账户
//        long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
        long accountNo = 18703442948L;
        //随机获取32位的数字+字母作为token
        String token = getStringNumRandom(32);
        //key的组成
        //order:submit:18703442948:Jrvkp8KCxN5HH8x40fyrAcy7kilYBHYf
        String key = String.format(RedisKey.SUBMIT_ORDER_TOKEN_KEY, accountNo, token);
        //令牌有效时间是30分钟
        redisTemplate.opsForValue().set(key, String.valueOf(Thread.currentThread().getId()), 30, TimeUnit.MINUTES);
        return token;
    }
    private static final String ALL_CHAR_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 获取随机长度的串
     *
     * @param length 长度
     * @return 随机串
     */
    public static String getStringNumRandom(int length) {
        //生成随机数字和字母,
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {
            saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
        }
        return saltString.toString();
    }

}
