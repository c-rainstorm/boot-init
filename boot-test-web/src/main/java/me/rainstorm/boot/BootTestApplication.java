package me.rainstorm.boot;

import me.rainstorm.boot.lifecycle.application.ApplicationEventProcessor;
import me.rainstorm.boot.lifecycle.application.ApplicationInit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

/**
 * {@link EnableConfigurationProperties} 的作用是为自定义的配置提供智能提示 {@link org.springframework.boot.context.properties.ConfigurationProperties @ConfigurationProperties}
 * 和智能跳转到配置类的功能
 * 生效方式：
 * 1. 自定义新的配置类
 * 2. maven clean
 * 3. 运行项目
 * <p>
 * 在项目运行时会在模块 target/classes/META-INF 下生成 spring-configuration-metadata.json 文件，
 *
 * @see me.rainstorm.boot.config.SelfDefineProperties - 自动提示 me.rainstorm.boot 前缀的配置
 * @see me.rainstorm.boot.dao.config.BootDataSourceConfig - 自动提示 me.rainstorm.boot.datasource 前缀配置
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 */
@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties
public class BootTestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootTestApplication.class)
                .initializers(new ApplicationInit())
                .listeners(new ApplicationEventProcessor())
                .build().run(args);
    }

}
