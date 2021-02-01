package com.rabbit.producer.component;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RabbitSender rabbitSender;

    @Test
    public void test11()throws Exception{

        Map<String,Object> properties = new HashMap<String, Object>();
        properties.put("111","22222");

        rabbitSender.send("hello111",properties);


    }
}
