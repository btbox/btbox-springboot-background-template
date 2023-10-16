package com.btbox;

import cn.xuyanwu.spring.file.storage.spring.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * 启动程序
 *
 * @author ruoyi
 */

@EnableFileStorage
@SpringBootApplication
public class BtBoxBootApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BtBoxBootApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  BTBOX-BOOT启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
