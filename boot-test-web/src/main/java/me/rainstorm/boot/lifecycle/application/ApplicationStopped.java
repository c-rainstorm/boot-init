package me.rainstorm.boot.lifecycle.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * @author baochen1.zhang
 * @date 2019.09.01
 */
@Slf4j
@Component
public class ApplicationStopped implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        log.info("onApplicationEvent.ContextStoppedEvent");
    }
}
