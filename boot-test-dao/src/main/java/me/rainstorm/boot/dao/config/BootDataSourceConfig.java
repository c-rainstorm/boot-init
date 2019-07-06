package me.rainstorm.boot.dao.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Configuration(value = "bootDataSourceConfig")
@ConfigurationProperties(prefix = BootDataSourceConfig.BOOT_DATA_SOURCE_PREFIX)
public class BootDataSourceConfig extends BaseDataSourceConfig {
    public static final String BOOT_DATA_SOURCE_PREFIX = "me.rainstorm.boot.datasource";
}
