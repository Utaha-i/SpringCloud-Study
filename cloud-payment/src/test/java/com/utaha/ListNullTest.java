package com.utaha;



import cn.hutool.core.util.ObjectUtil;
import com.utaha.pojo.Person;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class ListNullTest {


    public static void main(String[] args) {

        Person person = new Person();
        ArrayList<Person> list = new ArrayList<>();
        list.add(person);
        list.stream().filter( p -> {
            AtomicBoolean flag = new AtomicBoolean(false);
            Class<? extends Person> clazz = p.getClass();
            Field[] fields = clazz.getFields();
            Arrays.stream(fields).forEach(item -> {
                if (item == null){
                    flag.set(true);
                }
            });
            return flag.get();
        });

        if(ObjectUtil.isNull(list)){
            System.out.println("*************");
        }
    }
}
