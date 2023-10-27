package com.utaha.kazusa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableFeignClients
public class CloudOrder {
    public static void main(String[] args) {
        SpringApplication.run(CloudOrder.class, args);
    }
}