package me.rainstorm.boot.lifecycle.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author baochen1.zhang
 * @date 2019.09.01
 */
@Slf4j
@Component
public class ApplicationClosed implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("onApplicationEvent.ContextClosedEvent");
    }
}
