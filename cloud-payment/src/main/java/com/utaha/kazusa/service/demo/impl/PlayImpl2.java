package com.utaha.kazusa.service.demo.impl;

import com.utaha.kazusa.service.demo.Play;
import org.springframework.stereotype.Component;

@Component
public class PlayImpl2 implements Play {
    @Override
    public void play() {
        System.out.println("玩2");
    }
}
