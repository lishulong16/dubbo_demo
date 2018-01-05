package net.dubbo.common.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import net.dubbo.common.utils.Constants;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/28
 * @doing
 */
@JsonRootName(Constants.ERROR_RESPONSE)
public class ErrorResponse {

    private String code;
    private String msg;
    private String sub_code;
    private String sub_msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }
}
