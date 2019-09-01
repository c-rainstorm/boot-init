package me.rainstorm.boot.lifecycle.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @author baochen1.zhang
 * @date 2019.09.01
 */
@Slf4j
@Component
public class ApplicationStarted implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        log.info("onApplicationEvent.ContextStartedEvent");
    }
}
