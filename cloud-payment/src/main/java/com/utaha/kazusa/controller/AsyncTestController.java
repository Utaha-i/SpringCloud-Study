package com.utaha.kazusa.controller;

import com.utaha.kazusa.service.demo.SayService;
import com.utaha.kazusa.service.order.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class AsyncTestController {
    @Autowired
    private DemoService demoService;

    @Autowired
    private List<SayService> sayServiceList;

    @RequestMapping("/test/async")
    public String callServiceMethod() {
        demoService.doSth();
        demoService.doSth();
        demoService.doSth();
        demoService.doSth();
        demoService.doSth();
        return "success";
    }

    @GetMapping("/say")
    public String sayTest() {
        sayServiceList.forEach(SayService::say);
        return "success";
    }

}
