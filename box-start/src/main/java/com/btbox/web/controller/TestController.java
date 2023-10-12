package com.btbox.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 * @description:
 * @author: BT-BOX
 * @createDate: 2023/10/12 15:04
 * @version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    /**
     * t1测试
     */
    @GetMapping("t1")
    public void t1() {
        System.out.println(" t1 = t1");
    }

}