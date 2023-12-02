package com.utaha.kazusa.service.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoService {

    /**
     * 模拟异步方法
     */
    @Async
    public void doSth() {
        log.error("Demo Service " + Thread.currentThread().getName());
    }
}