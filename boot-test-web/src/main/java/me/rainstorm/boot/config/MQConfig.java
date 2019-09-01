package me.rainstorm.boot.config;

import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author baochen1.zhang
 * @date 2019.08.27
 */
@Profile("mq")
@Configuration
public class MQConfig {
    private static final String CATEGORY = MQConfig.class.getSimpleName();

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    @ConfigurationProperties(prefix = "me.rainstorm.boot.mq.consumer")
    public DefaultMQPushConsumer defaultMqPullConsumer() throws MQClientException {
        final String logMethodName = "defaultMqPullConsumer";
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();

        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(msg -> {
                LogUtil.info(LogBuilder.init(CATEGORY, logMethodName)
                        .setMessage(String.format("msgId: %s, body:%s", msg.getMsgId(), new String(msg.getBody()))).build());
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        return consumer;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    @ConfigurationProperties(prefix = "me.rainstorm.boot.mq.producer")
    public DefaultMQProducer defaultMqProducer() {
        return new DefaultMQProducer();
    }
}