package me.rainstorm.boot.lifecycle.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author baochen1.zhang
 * @date 2019.09.01
 */
@Slf4j
@Component
public class LifeCycleImpl implements SmartLifecycle {

    @Override
    public void start() {
        log.info("Lifecycle.start");
    }

    @Override
    public void stop() {
        log.info("Lifecycle.stop");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
