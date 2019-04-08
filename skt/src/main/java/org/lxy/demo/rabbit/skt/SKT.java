package org.lxy.demo.rabbit.skt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@SpringBootApplication
public class SKT {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SKT.class)
                .run(args);
    }
}
