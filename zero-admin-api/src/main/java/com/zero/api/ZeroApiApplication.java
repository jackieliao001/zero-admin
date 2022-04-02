package com.zero.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * web入口启动类
 *
 * @author liaojunjie
 * @date 2022/4/1 18:38
 */
@SpringBootApplication(scanBasePackages = {"com.zero"})
public class ZeroApiApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ZeroApiApplication.class);
        // 监控应用的PID，启动时可指定PID路径：--spring.pid.file=/home/zeroAdmin/app.pid 方便 kill，kill `cat /home/zeroAdmin/app.pid`
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
