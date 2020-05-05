package com.carry.main.producer;

import com.carry.main.config.DelayedRabbitMQConfig;
import com.carry.main.config.RabbitMQConfig;
import com.carry.main.enums.DelayTypeEnum;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DelayMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg, DelayTypeEnum type) {
        switch (type) {
            case DELAY_10s:
                rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME, RabbitMQConfig.DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case DELAY_60s:
                rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME, RabbitMQConfig.DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
        }
    }


    /**
     * @param msg        消息
     * @param delayTimes 延迟的时间，单位ms
     */
    public void sendMsg2(String msg, int delayTimes) {

        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME, RabbitMQConfig.DELAY_QUEUEC_ROUTING_KEY, msg, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                        return message;
                    }
                }
        );
    }

    /**
     * 终极解决方案，加入插件rabbitmq_delayed_message_exchange
     * rabbitmq-plugins enable rabbitmq_delayed_message_exchange
     *
     * @param message
     * @param delayTimes
     */
    public void sendMessage(String message, int delayTimes) {

        rabbitTemplate.convertAndSend(DelayedRabbitMQConfig.DELAYED_EXCHANGE_NAME,DelayedRabbitMQConfig.DELAYED_ROUTING_KEY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //1.这里有两种方式
                message.getMessageProperties().setDelay(delayTimes);
                //或者是用setHeader
//                message.getMessageProperties().setHeader("x-delay",delayTimes);
                return message;
            }
        });

    }
}