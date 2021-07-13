package com.cql.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequestMapping("/hello")
@RestController
public class HelloWorldController {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/getFromKey/{key}")
    @ResponseBody
    public String test(@PathVariable String key){
        String value = (String) redisTemplate.opsForValue().get(key);
        return "redis get结果 value=" + value;
    }
    @GetMapping("/set")
    @ResponseBody
    public String testSet(@PathParam("key") String key,@PathParam("value") String value){
        redisTemplate.opsForSet().add(key,value);
        return String.format("redis set成功！key=%S,value=%s",key,value);
    }
}
