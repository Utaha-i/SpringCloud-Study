package com.utaha;

import lombok.val;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("0.00000");

        String s = String.valueOf(bigDecimal);

        boolean contains = s.contains(".");

        System.out.println(contains);


    }

}
