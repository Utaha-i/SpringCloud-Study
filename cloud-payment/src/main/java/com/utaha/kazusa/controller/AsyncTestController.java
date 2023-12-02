package com.utaha.kazusa.controller;

import com.utaha.kazusa.service.order.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class AsyncTestController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("/test/async")
    public String callServiceMethod() {
        demoService.doSth();
        demoService.doSth();
        demoService.doSth();
        demoService.doSth();
        demoService.doSth();
        return "success";
    }
}
