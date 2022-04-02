package com.zero.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * web入口启动类
 *
 * @author liaojunjie
 * @date 2022/4/1 18:38
 */
@SpringBootApplication(scanBasePackages = {"com.zero"})
//@MapperScan("com.zero.**.mapper")
public class ZeroApiApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ZeroApiApplication.class);
        // 监控应用的PID，启动时可指定PID路径：--spring.pid.file=/home/eladmin/app.pid
        // 或者在 application.yml 添加文件路径，方便 kill，kill `cat /home/eladmin/app.pid`
//        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
        System.out.println("↖(^ω^)↗  零点启动成功！ ♪(＾∀＾●)ﾉ");
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
