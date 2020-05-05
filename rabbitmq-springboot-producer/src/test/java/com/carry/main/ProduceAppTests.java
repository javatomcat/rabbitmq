package com.carry.main;

import com.carry.main.entity.Order;
import com.carry.main.producer.OrderSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ProduceAppTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OrderSender orderSender;


    @Test
    public void testOrderSend1() throws Exception {
        Order order = new Order();
        order.setId(2020000001);
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderSender.send(order);
    }

}
