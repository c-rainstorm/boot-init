package me.rainstorm.boot.lifecycle.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author baochen1.zhang
 * @date 2019.09.01
 */
@Slf4j
@Component
public class BeanLifeCycleAllInOne implements BeanNameAware, ApplicationContextAware, BeanFactoryAware, EnvironmentAware, ResourceLoaderAware {

    @Override
    public void setBeanName(String name) {
        log.info("BeanNameAware.setBeanName -- {}", name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("ApplicationContextAware.setApplicationContext");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("BeanFactoryAware.setBeanFactory");
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("EnvironmentAware.setEnvironment");
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        log.info("ResourceLoaderAware.setResourceLoader");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("BeanLifeCycleAllInOne.postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("BeanLifeCycleAllInOne.preDestroy");
    }
}
