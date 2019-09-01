package me.rainstorm.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.util.JsonUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author baochen1.zhang
 * @date 2019.08.27
 */
@Api("MQ 测试Controller")
@RestController
@RequestMapping("mq")
@Profile("mq")
public class MQController {
    @Resource
    private DefaultMQProducer mqProducer;

    @ApiOperation("发送消息")
    @PostMapping("produce")
    public SendResult produce(@RequestBody User user) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {

        Message message = new Message("TopicTest", "default",
                JsonUtil.toJsonString(user).getBytes(StandardCharsets.UTF_8));

        return mqProducer.send(message);
    }
}
