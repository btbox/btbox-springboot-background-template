package com.btbox.web.controller;

import com.btbox.common.annotation.Log;
import com.btbox.common.enums.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: BT-BOX
 * @createDate: 2023/10/9 17:18
 * @version: 1.0
 */
@RequiredArgsConstructor
@RestController
@Validated
public class TestController {

    @GetMapping("t1")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    public void t1(User user) {
        System.out.println("name = " + user);
    }

}