package com.lhx.springcloud.blockchain.bean;


import com.lhx.springcloud.blockchain.model.Permission;

import java.util.List;

public class PermissionData extends BaseData {
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
