package me.rainstorm.boot.domain.constant;

/**
 * @author baochen1.zhang
 * @date 2019.07.04
 */
public enum ResponseCodeEnum {
    /**
     * AC
     */
    ACCEPT("0", "请求已接受"),
    /**
     * AC
     */
    SUCCESS("0", "成功"),

    // ----------------------  通用异常码  --------------------------

    /**
     * 400 Bad Request
     */
    BAD_REQUEST("400", "400 Bad Request"),

    /**
     * 资源未找到
     */
    NOT_FOUND("404", "资源未找到"),

    /**
     * 内部服务器错误
     */
    INTERVAL_SERVER_ERROR("500", "内部服务器错误"),

    // ----------------------  专用异常码   -------------------------
    CONCURRENT_OPERATE_ERROR("COMMON_500_0000", "并发操作异常"),
    /**
     * 用户已存在
     */
    USER_EXISTS("USER_EXISTS", "用户已存在");


    private String code;
    private String desc;

    ResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
