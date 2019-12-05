package com.example.springboot_redis;

import com.example.springboot_redis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqTest {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqTest.class);

    @Autowired
    public AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        String context = "hello！I'm iron man." + new Date();
        log.info("Sender:" + context);
        User user =new User();
        user.setName("张三");
        user.setAge(1);
        user.setSex("男");
        this.amqpTemplate.convertAndSend("fanoutExchange","abcd.ee", user);
    }

    @RabbitListener(queues="fanout.A")
    public void process1(User user){
        log.info("======Receiver1:"+user.getName()+"========");
    }

    @RabbitListener(queues="fanout.B")
    public void process2(User user){
        log.info("======Receiver2:"+user.getSex()+"========");
    }

    @RabbitListener(queues="fanout.C")
    public void process3(User user){
        log.info("======Receiver3:"+user.getAge()+"========");
    }
}
