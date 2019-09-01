package me.rainstorm.boot.lifecycle.container;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author baochen1.zhang
 * @date 2019.06.23
 */
@Slf4j
@Component
public class AfterBeanFactoryInitial implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("BeanFactoryPostProcessor.postProcessBeanFactory");
    }
}
