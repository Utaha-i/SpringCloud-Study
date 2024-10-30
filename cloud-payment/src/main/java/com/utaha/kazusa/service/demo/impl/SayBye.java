package com.utaha.kazusa.service.demo.impl;

import com.utaha.kazusa.service.demo.SayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * 原型Bean注入到单例Bean中，会引原型模式不会生效
 */
@Service
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SayBye extends SayService {
    @Override
    public void say() {
        super.say();
        log.info("Bye World");
    }
}
