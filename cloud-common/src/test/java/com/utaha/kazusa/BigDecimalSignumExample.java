package com.utaha.kazusa;

import java.math.BigDecimal;

public class BigDecimalSignumExample {
    public static void main(String[] args) {
        BigDecimal positiveNumber = new BigDecimal("10.5");
        BigDecimal negativeNumber = new BigDecimal("-10.5");
        BigDecimal zero = BigDecimal.ZERO;
        //signum()方法判断 正负零
        System.out.println("Positive number sign: " + positiveNumber.signum());  // 输出: 1
        System.out.println("Negative number sign: " + negativeNumber.signum()); // 输出: -1
        System.out.println("Zero sign: " + zero.signum());                       // 输出: 0

        //negate() 正负转化，转化为相反数
        BigDecimal num1 = new BigDecimal("10.5");
        System.out.println(num1);
        System.out.println(num1.negate());

    }
}