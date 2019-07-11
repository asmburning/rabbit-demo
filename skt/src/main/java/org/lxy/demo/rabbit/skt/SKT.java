package org.lxy.demo.rabbit.skt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@SpringBootApplication
public class SKT {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
                .sources(SKT.class)
                .run(args);
    }
}
