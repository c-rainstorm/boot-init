package me.rainstorm.boot.lifecycle.application;

import org.springframework.context.ApplicationEvent;

/**
 * @author baochen1.zhang
 * @date 2019.09.02
 */
public class JacksonConverterConfigDoneEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public JacksonConverterConfigDoneEvent(Object source) {
        super(source);
    }
}
