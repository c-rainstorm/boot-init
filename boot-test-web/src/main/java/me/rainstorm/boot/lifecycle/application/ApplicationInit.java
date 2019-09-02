package me.rainstorm.boot.lifecycle.application;

import lombok.extern.slf4j.Slf4j;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Application 启动之前的一些初始化，需要手动在 BootTestApplication 里进行
 *
 * @author baochen1.zhang
 * @date 2019.08.18
 */
@Slf4j
public class ApplicationInit implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final String CATEGORY = ApplicationInit.class.getSimpleName();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final String logMethodName = "initialize";

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("from shutdown hook");
        }));

//        environment.
        // do something
        LogUtil.info(LogBuilder.init(CATEGORY, logMethodName).setMessage("初始化方法调用").build());
    }
}
