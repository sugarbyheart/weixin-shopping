package org.hamster.weixinmp;
import org.hamster.weixinmp.constant.LinkTypeEnum;
import org.hamster.weixinmp.service.LinkMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@SpringBootApplication
@EnableScheduling
public class App {

    @Autowired
    private LinkMessageService linkMessageService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

//    @Override
//    public void run(String... arg0) throws Exception {
//        linkMessageService.addLink("sdf", "dsff", 11L, LinkTypeEnum.Letian);
//    }
}