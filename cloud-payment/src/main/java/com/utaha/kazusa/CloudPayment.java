package com.utaha.kazusa;

import com.utaha.kazusa.common.dao.DummyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication()
@EnableAsync
@MapperScan(basePackages = {"com.utaha.kazusa.dao.mapper"}, basePackageClasses = DummyMapper.class)
public class CloudPayment {
    public static void main(String[] args) {
        SpringApplication.run(CloudPayment.class, args);
    }
}
