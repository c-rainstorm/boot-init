package me.rainstorm.boot.domain.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
public class JsonUtil {
    private static final String CATEGORY = JsonUtil.class.getSimpleName();

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();

        // 设置输出时包含属性的风格
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 禁止使用int代表Enum的order()來反序列化Enum,非常危險
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);

        // 注册 Java8Module
        // 项目日期类统一使用 jsr310 格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)));
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)));

        objectMapper.registerModule(javaTimeModule);
        objectMapper.registerModule(new Jdk8Module());

        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    public static String toJson(Object obj) throws JsonProcessingException {
        if (Objects.isNull(obj)) {
            return "null";
        }

        if (obj instanceof String) {
            return (String) obj;
        }

        return objectMapper.writeValueAsString(obj);
    }

    public static String toJsonString(Object obj) {
        final String logMethodName = "toJsonString";
        if (Objects.isNull(obj)) {
            return "null";
        }

        if (obj instanceof String) {
            return (String) obj;
        }

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LogUtil.error(LogBuilder.init(CATEGORY, logMethodName)
                    .setMessage("序列化对象失败", e).build());
            return "";
        }
    }

    public static <T> T fromJson(String json, Class<T> kls) throws IOException {
        return objectMapper.readValue(json, kls);
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(json, typeReference);
    }
}
