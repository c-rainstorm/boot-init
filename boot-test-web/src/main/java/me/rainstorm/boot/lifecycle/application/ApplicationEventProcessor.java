package me.rainstorm.boot.lifecycle.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author baochen1.zhang
 * @date 2019.09.02
 */
@Slf4j
public class ApplicationEventProcessor implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info(event.getClass().getName());
    }
}
