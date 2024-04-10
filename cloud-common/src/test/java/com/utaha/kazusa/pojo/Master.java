package com.utaha.kazusa.pojo;

import lombok.Data;

@Data
public class Master {
    //主宰的初始血量
    private int blood = 100;

    //每次被击打后血量减5
    public int decreaseBlood() throws Exception {
        if(blood <= 0){
            throw new Exception("主宰已经被击败！");
        }
        blood = blood - 5;
        return blood;
    } 
}