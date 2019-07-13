package me.rainstorm.boot.config;

import lombok.Data;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Configuration
@ConfigurationProperties(prefix = SelfDefineProperties.SELF_DEFINE_GLOBAL_PREFIX)
@Data
public class SelfDefineProperties {
    private static final String CATEGORY = SelfDefineProperties.class.getSimpleName();
    public static final String SELF_DEFINE_GLOBAL_PREFIX = "me.rainstorm.boot";

    /**
     * AOP 日志中，输出到日志中的类名前缀
     * <p>
     * 默认 me.rainstorm.boot.domain 下所有类都会输出，若需要定制，请定义以下配置
     * <p>
     * me.rainstorm.boot.log-params-prefix = me.rainstorm.boot.domain,me.java.lang
     */
    private String[] logParamsPrefix = {"me.rainstorm.boot.domain"};

    /**
     * DAO 方法调用执行时间默认日志级别为 INFO，若实际执行时间超过该阈值，则切换为 Warn 日志
     * 后续考虑不超过阈值不输出日志
     *
     */
    private Integer daoAccessTimeWarnThreshold = 200;

    @PostConstruct
    private void post() {
        LogUtil.info(LogBuilder.init(CATEGORY, "post")
                .setMessage(String.format("daoAccessTimeWarnThreshold: %s", daoAccessTimeWarnThreshold)).build());
    }
}
