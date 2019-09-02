package me.rainstorm.boot.lifecycle.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author baochen1.zhang
 * @date 2019.09.02
 */

@Slf4j
@Component
public class EventPublisherImpl implements ApplicationContextAware, ApplicationEventPublisher {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void publishEvent(Object event) {
        applicationContext.publishEvent(event);
    }
}

