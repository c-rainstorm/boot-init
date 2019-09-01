package me.rainstorm.boot.lifecycle.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author baochen1.zhang
 * @date 2019.06.23
 */
@Slf4j
@Component
public class AfterBeanInitial implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (BeanLifeCycleAllInOne.class.getSimpleName().equalsIgnoreCase(beanName)) {
            log.info("AfterBeanInitial.postProcessBeforeInitialization -- {}", beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (BeanLifeCycleAllInOne.class.getSimpleName().equalsIgnoreCase(beanName)) {
            log.info("AfterBeanInitial.postProcessAfterInitialization -- {}", beanName);
        }
        return bean;
    }
}
