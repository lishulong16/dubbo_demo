package net.dubbo.common.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import net.dubbo.common.utils.Constants;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/28
 * @doing
 */
@JsonRootName(Constants.PARKING_BASIC_DATA_SYNC_RESPONSE)
public class BasicDataSyncResponse {

    private String park_id;

    private String message;

    private int code;

    private Boolean successful;

    public String getPark_id() {
        return park_id;
    }

    public void setPark_id(String park_id) {
        this.park_id = park_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }
}
