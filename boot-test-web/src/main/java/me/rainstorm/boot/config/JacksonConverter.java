package me.rainstorm.boot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import me.rainstorm.boot.domain.util.DateTimeFormatterUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * MVC 自动序列化 & 反序列化的 ObjectMapper
 * 与 {@link me.rainstorm.boot.domain.util.JsonUtil JsonUtil} 中的 ObjectMapper 分开实现，方便针对不同需求进行优化
 *
 * @author baochen1.zhang
 * @date 2019.07.06
 */
@Configuration
public class JacksonConverter {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // 设置输出时包含属性的风格
        // 只输出非空
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 禁止使用int代表Enum的order()來反序列化Enum,非常危險
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);

        // 注册 Java8Module
        // 项目日期类统一使用 jsr310 格式, 指定序列化反序列化的格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)));
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)));

        objectMapper.registerModule(javaTimeModule);
        objectMapper.registerModule(new Jdk8Module());

        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        return objectMapper;
    }
}
