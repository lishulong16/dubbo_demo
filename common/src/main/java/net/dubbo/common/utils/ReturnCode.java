package net.dubbo.common.utils;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/28
 * @doing
 */
public enum ReturnCode {

    SUCCESS(0x00, "success"),
    SYSTEM_ERROR(0x10, "system error");

    private int code;

    private String propertyDesc;

    ReturnCode(int code, String propertyDesc) {
        this.code = code;
        this.propertyDesc = propertyDesc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPropertyDesc() {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public static void main(String[] args) {
        System.out.println(ReturnCode.SUCCESS.getCode());
        System.out.println(ReturnCode.SYSTEM_ERROR.getCode());
    }
}
