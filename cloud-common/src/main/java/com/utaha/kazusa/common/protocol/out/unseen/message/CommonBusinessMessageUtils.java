package com.utaha.kazusa.common.protocol.out.unseen.message;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * default（包访问权限）
 */
class CommonBusinessMessageUtils {

    static <SOURCE, TARGET> TARGET create(Class<SOURCE> sourceClass, Class<? super TARGET> targetCalssSuperClass) throws IllegalAccessException {
        //获取到资源类（带泛型信息）
        ParameterizedType parameterizedType = getParameterizedType(sourceClass);
        //获取 ActualTypeArguments 实际类型参数（带泛型）
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        //解析类，获取目标
        Class<TARGET> targetClass = (Class<TARGET>) resolveClass(actualTypeArguments, targetCalssSuperClass);
        //开始创建Response
        final TARGET target;
        try {
            target = targetClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return target;
    }

    /**
     * 获取资源类的泛型信息parameterizedType
     *
     * @param sourceClass 资源类
     * @param <SOURCE>    资源类
     * @return ParameterizedType 参数化类型（保存泛型）
     * @throws IllegalAccessException 非法访问异常
     */
    private static <SOURCE> ParameterizedType getParameterizedType(Class<SOURCE> sourceClass) throws IllegalAccessException {
        //获取父类信息
        Type superclassType = sourceClass.getGenericSuperclass();
        //判断是否有父类
        if (superclassType == null) {
            throw new IllegalAccessException(sourceClass + "Super class's type can't be null");
        }
        //判断是否有泛型
        if (!(superclassType instanceof ParameterizedType)) {
            throw new IllegalAccessException(sourceClass + "Super class's type must be java.lang.reflect.ParameterizedType");
        }
        //Type 强转为 子类ParameterizedType
        ParameterizedType parameterizedType = (ParameterizedType) superclassType;
        return parameterizedType;
    }

    /**
     * 解析类 获取目标对象
     *
     * @param actualTypeArguments 实际类型参数（带泛型）
     * @param targetClassSuperClass
     * @param <TARGET>
     * @return
     */
    private static <TARGET> Class<?> resolveClass(Type[] actualTypeArguments, Class<? super TARGET> targetClassSuperClass) {
        for (Type type : actualTypeArguments) {
            Class<?> clazz = (Class<?>) type;
            if (targetClassSuperClass.isAssignableFrom(clazz)) {
                return clazz;
            }
        }
        throw new IllegalStateException("can't find " + AbstractCommonBusinessMessageResponse.class +
                "in actual type arguments " + Arrays.toString(actualTypeArguments));
    }

}
