package com.bboyhan.common.enums;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 18:37
 */
public enum ResultCodeEnum {

    SUCCESS(1, "success"),
    ERROR(0, "error"),
    /**
     * 10000 - 19999 用户相关
     */
    USR_10000(10000, "用户Id不能为空"),
    /**
     * 20000 - 29999 权限相关
     */
    PEM_20000(20000, "无访问权限"),
    /**
     * 30000 - 39999 商品相关
     */
    ORD_30000(30000, "商品不存在"),
    /**
     * 40000 - 49999 菜单相关
     */
    MENU_40000(40000, "菜单不存在"),
    /**
     * 50000 - 59999 系统相关
     */
    SYS_50000(50000, "系统错误");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCodeEnum getEnum(int code) {
        for (ResultCodeEnum ele : ResultCodeEnum.values()) {
            if (ele.getCode() == code) {
                return ele;
            }
        }
        return null;
    }

    public static String getMsg(int code) {
        for (ResultCodeEnum ele : ResultCodeEnum.values()) {
            if (ele.getCode() == code) {
                return ele.getMsg();
            }
        }
        return null;
    }

}
