package me.rainstorm.boot.config;

import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.cache.CacheManager;
import java.io.IOException;

/**
 * @author baochen1.zhang
 * @date 2019.08.25
 */
@Configuration
public class EhcacheConfig {
    private static final String CATEGORY = EhcacheConfig.class.getSimpleName();

    @Bean(name = "ehcacheCacheManager", destroyMethod = "close")
    public CacheManager cacheManager() throws IOException {
        final String logMethodName = "cacheManager";

        ClassPathResource resource = new ClassPathResource("ehcache.xml");
        // 使用系统类加载器加载，防止put不进去&&对Cache进行启动时初始化
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        LogUtil.info(LogBuilder.init(CATEGORY, logMethodName)
                .setMessage(String.format("classloader: %s", classLoader)).build());

        XmlConfiguration xmlConfiguration = new XmlConfiguration(resource.getURL(), classLoader);

        return new EhcacheCachingProvider().getCacheManager(resource.getURI(), xmlConfiguration);
    }
}
