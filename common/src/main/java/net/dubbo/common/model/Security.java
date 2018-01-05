package net.dubbo.common.model;

import java.io.Serializable;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/28
 * @doing
 */
public class Security implements Serializable {

    private String securityKey;

    private String securityValue;

    private String merchantid;

    private String tag;

    private Integer isDelete;

    private String createUser;

    private String updateUser;

    private String remark;

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getSecurityValue() {
        return securityValue;
    }

    public void setSecurityValue(String securityValue) {
        this.securityValue = securityValue;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
