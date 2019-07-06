package me.rainstorm.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 将配置文件中的配置添加到 Environment，可以追加多个 @PropertySource 来实现，不支持通配符
 *
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Configuration
@PropertySource("classpath:application-datasource.properties")
public class MultiConfigFilesLoader {
}
