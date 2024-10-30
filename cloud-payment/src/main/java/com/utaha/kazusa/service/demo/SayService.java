package com.utaha.kazusa.service.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 实验单例模式
 * 原型Bean注入到单例Bean中，会引原型模式不会生效
 */
@Slf4j
public abstract class SayService {
    List<String> data = new ArrayList<>();
    public void say() {
        data.add(IntStream.rangeClosed(1, 10000)
                .mapToObj(a -> "a")
                .collect(Collectors.joining("")) + UUID.randomUUID());

        log.info("I'm {} Size {}", this, data.size());
    }
}
