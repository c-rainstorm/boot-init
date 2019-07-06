package me.rainstorm.boot.domain.constant;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
public enum GenderEnum {
    /**
     * 男性
     */
    MALE(1, "男性"),
    /**
     * 女性
     */
    FEMALE(0, "女性");


    GenderEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
