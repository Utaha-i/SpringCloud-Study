package com.utaha.kazusa.annotation;

import java.lang.annotation.*;

/**
 * 自定义防重提交
 */
@Documented
@Target(ElementType.METHOD)//可以用在方法上
@Retention(RetentionPolicy.RUNTIME)//保留到虚拟机运行时,可通过反射获取
public @interface RepeatSubmit {

    /**
     * 默认防重提交，是方法参数
     */
    Type limitType() default Type.PARAM;
    /**
     * 加锁过期时间，默认是5秒
     */
    long lockTime() default 5L;
    /**
     * 防重提交，支持两种，一个是方法参数，一个是令牌
     */
    enum Type { PARAM, TOKEN }
}
