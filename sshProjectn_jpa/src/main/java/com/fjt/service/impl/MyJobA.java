package com.fjt.service.impl;

import java.util.Date;

import org.springframework.stereotype.Component;
//跑批的方法
@Component
public class MyJobA {
	public void work() {
        System.out.println("date: " + new Date().getTime());
    }


}
