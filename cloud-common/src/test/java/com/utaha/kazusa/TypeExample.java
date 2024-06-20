package com.utaha.kazusa;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 可见，通过Type可以保存Class的所有接口实现和类继承信息
 * 由于Class中的泛型信息会被擦除，TypeName只保留了类声明时的泛型信息(<String>变为<E>)
 * 另外可以发现，带泛型的Type都是通过ParameterizedType类型保存的，无泛型的Type则是通过Class来保存的
 * <p>
 * 相比Class只保存基本类型信息，ParameterizedType还会保存泛型参数等额外的类型信息
 */
public class TypeExample {

    public String name;
    public List<String> list;
    public Map<Integer, Long> map;
    public Map.Entry<Integer, Long> entry;

    public static void main(String[] args) throws NoSuchFieldException {

        List<String> list = new LinkedList();
        Class<? extends List> clazz = list.getClass();

        //获取接口类型信息
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces)
            System.out.println("获取接口类型信息-Generic Interface：" + genericInterface.getTypeName());

        //打印Type的具体类型
        for (Type genericInterface : genericInterfaces)
            System.out.println("Type的具体类型-Type Class：" + genericInterface.getClass().getName());

        //todo 怎么取类的未擦除的泛型信息呢？
        for (Type genericInterface : genericInterfaces) {
            Class<? extends Type> aClass = genericInterface.getClass();
            System.out.println(aClass);
        }

        //获取父类类型信息
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println("Generic Superclass：" + genericSuperclass.getTypeName());

        System.out.println("*******************");

        Field field = TypeExample.class.getDeclaredField("entry");
        ParameterizedType type = (ParameterizedType) field.getGenericType();

        //获取字段的基本类型
        Type rawType = type.getRawType();
        System.out.println("Raw Type: " + rawType.getTypeName());

        //获取字段泛型参数的类型
        Type[] argumentTypes = type.getActualTypeArguments();
        for (Type argumentType : argumentTypes)
            System.out.println("Argument Type: " + argumentType.getTypeName());

        //如果字段类型是个内部类，则获取字段所在外部类的类型
        Type ownerType = type.getOwnerType();
        System.out.println("Owner Type: " + ownerType.getTypeName());

    }
}