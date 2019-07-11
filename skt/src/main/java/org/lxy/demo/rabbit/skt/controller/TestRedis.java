package org.lxy.demo.rabbit.skt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author liuxinyi
 * @date 2019-06-28
 */
@RestController
@RequestMapping("/redis")
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    private Jedis jedis = new Jedis("127.0.0.1", 6379);


    @RequestMapping("/testSet")
    public Object testSet() {
        String key = "key";
        return jedis.set(key, key, "NX", "EX", 20);
    }
}
