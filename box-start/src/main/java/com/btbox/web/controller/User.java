package com.btbox.web.controller;

import com.btbox.common.xss.Xss;
import lombok.Data;

/**
 * @description:
 * @author: BT-BOX
 * @createDate: 2023/10/9 17:34
 * @version: 1.0
 */
@Data
public class User {
    @Xss(message = "不允许脚本")
    private String name;

}