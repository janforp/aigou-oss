package com.aigou.oss.model.dto;

import com.aigou.oss.model.OssAdmin;

/**
 * Created by wuqiang on 16-2-2.
 *
 * @author wuqiang
 */
public class OssAccountPageDto extends OssAdmin {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
