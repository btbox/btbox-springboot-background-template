package com.btbox.demo.controller;

import com.btbox.common.annotation.Log;
import com.btbox.common.enums.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
public class TestLogController {

    /**
     * 日志保存在 sys_oper_log 表中
     * @author: BT-BOX(HJH)
     * @param name
     * @version: 1.0
     * @createDate: 2023/10/10 16:24
     * @return: void
     */
    @GetMapping("t1")
    @Log(title = "测试日志保存", businessType = BusinessType.INSERT)
    public void t1(String name) {
        System.out.println("name = " + name);
    }

}