package com.example.springboot_redis;

import com.example.springboot_redis.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.springboot_redis.util.Redis;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    @Autowired
    private Redis redis;

    private User user;

    @Test
    public void test(){
        if (!redis.hasKey("litaijie")) {
            redis.set("litaijie", "nihao");
        }
        redis.expire("litaijie",30);
        System.out.println(redis.get("litaijie"));
    }

    @Before
    public void initUserInfo(){
        User user =new User();
        user.setName("张三");
        user.setAge(18);
        user.setSex("男");
        this.user = user;
    }

    @Test
    public void testEntity(){
        redis.set("user",user);
        System.out.println(redis.get("user"));
        redis.set("testIncr",1);
        redis.incr("testIncr",10L);
        System.out.println(redis.get("testIncr"));
    }
}
