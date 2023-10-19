# 							btbox-boot 后台模板

​																					[![JDK](https://camo.githubusercontent.com/a58e705e8721ac188fb1f5e4c43e49156aa4b1585726d1a64dc122d562dde35b/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a444b2d312e382b2d79656c6c6f77) ](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)[![License](https://camo.githubusercontent.com/83d3746e5881c1867665223424263d8e604df233d0a11aae0813e0414d433943/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d4d49542d626c75652e737667) ](https://opensource.org/licenses/mit-license.php)

## 项目介绍

用来快速启动 SpringBoot 项目的一个模板，大大减少开发时间，内置大部分工具(里面是大部分工具类是借鉴 [RuoYi-Vue-Plus](https://gitee.com/dromara/RuoYi-Vue-Plus) 后台框架)感谢作者!



## 版本分支

- 当前 BTBOX-BOOT 版本为 1.0.0

| 分支名称               | 说明                                                  |
| ---------------------- | ----------------------------------------------------- |
| 1.x-boot2.x-db-notauth | BTBOX-BOOT1.x，springboot2.x ，可连接数据库，没有权限 |
| 1.x-boot3.x-db-noauth  | BTBOX-BOOT1.x，springboot3.x ，可连接数据库，没有权限 |



## btbox-boot 有哪些用处、特性？

- 切换分支达到快速启动 SpringBoot
- 日志记录、Redis分布式锁、数据字典、限流、防重提交、Xss
- i18n ：可通过配置来进行返回翻译
- 封装了大部分工具类，例如 MybatisPlusMapper，Jackson工具类
- MDC日志追踪链路



## 模块和目录介绍

| 名称                  | 说明                             |
| --------------------- | -------------------------------- |
| btbox-common          | 公共模块，工具类等               |
| btbox-demo            | 示例模块                         |
| btbox-extend          | 扩展模块                         |
| - btbox-monitor-admin | springbootAdmin模块,用来监控     |
| btbox-framework       | 核心模块，重要配置类             |
| btbox-start           | 启动模块，用于汇总其他模块和启动 |
| btbox-system          | 系统模块                         |
| script                | 存放脚本                         |
| - db                  | 数据库脚本                       |



## 快速开始

- 下列使用 h2 数据库示例

### 1. 启动 SpringBoot

- 启动 btbox-start 下的 SpringBoot 程序，会自动生成数据库在当前项目目录 db 下

### 2. 执行SQL脚本

- 启动成功后访问 http://localhost:8090/h2 , jdbc 填入 `jdbc:h2:./db/btbox-boot;MODE=MYSQL;AUTO_SERVER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;`   用户密码登录 root/root 

- 在 script/db 文件夹下找到 h2 数据库类型脚本并执行

### 3. 测试保存日志

- 访问 http://localhost:8080/demo/log/save?name=test
- 查看数据库表 sys_oper_log 是否有日志数据产生



### 4. MybatisPlusMapper

#### 4.1 新建表 test_demo

```sql
drop table if exists `test_demo`;
create table `test_demo`
(
    `id`                varchar2(32)  not null COMMENT 'id',
    `name`               varchar2(20) not null COMMENT '名称',
    `age`              bigint(20)    default null COMMENT '年龄'
    PRIMARY KEY (`id`)
) comment ='测试表';
```



#### 4.2 新建实体类 TestDemo

```java
@Data
@Schema(description="测试表")
@TableName(value = "TEST_DEMO")
public class TestDemo {
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    @Schema(description="id")
    private String id;

    /**
     * 名称
     */
    @TableField(value = "NAME")
    @Schema(description="名称")
    private String name;
    
    /**
     * 年龄
     */
    @TableField(value = "AGE")
    @Schema(description="年龄")
    private Long age;
}
```



#### 4.3 新建Mapper

```java
public interface TestDemoMapper extends BaseMapperPlus<TestDemoMapper, TestDemo, TestDemo> {
}
```



#### 4.4 新建Controller

```java
@RequiredArgsConstructor
@RestController
@RequestMapping("test-demo")
@Tag(name = "测试接口")
public class TestDemoController {
    
    private final TestDemoMapper testDemoMapper;

    @Operation(summary = "保存")
    @GetMapping("save")
    public void save() {
        TestDemo demo = TestDemo();
        demo.setId("1");
        demo.setName("你好");
        demo.setAge(1);
        testDemoMapper.insertOrUpdate(demo);
    }
    
    @Operation(summary = "保存")
    @GetMapping("save")
    public R<TestDemo> getOne() {
        TestDemo demo = agvLogisticsStatMapper.selectOne(new LambdaQueryWrapper<TestDemo>().eq(TestDemo::getId, "1"));
        return R.ok(demo)
    }
    
}
```





```java
package com.btbox.demo.controller;

import com.btbox.common.annotation.Log;
import com.btbox.common.enums.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/demo/log")
public class TestLogController {

    /**
     * 日志保存在 sys_oper_log 表中
     * @author: BT-BOX(HJH)
     * @param name
     * @version: 1.0
     * @createDate: 2023/10/10 16:24
     * @return: void
     */
    @GetMapping("save")
    @Log(title = "测试日志保存", businessType = BusinessType.INSERT)
    public void save(String name) {
        System.out.println("name = " + name);
    }

}
```





## 鸣谢

- [RuoYi-Vue-Plus ](https://gitee.com/dromara/RuoYi-Vue-Plus)
