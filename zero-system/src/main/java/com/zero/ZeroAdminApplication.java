package com.zero;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台启动入口
 *
 * @author liaojunjie
 * @date 2022/3/28 16:51
 */
@EnableAsync
@RestController
@SpringBootApplication
@EnableTransactionManagement
public class ZeroAdminApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ZeroAdminApplication.class);
        // 监控应用的PID，启动时可指定PID路径：--spring.pid.file=/home/eladmin/app.pid
        // 或者在 application.yml 添加文件路径，方便 kill，kill `cat /home/eladmin/app.pid`
//        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

/*    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }*/

/*    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
        return fa;
    }*/

    /**
     * 访问首页提示
     *
     * @return /
     */
/*    @AnonymousGetMapping("/")
    public String index() {
        return "Backend service started successfully";
    }*/
}
