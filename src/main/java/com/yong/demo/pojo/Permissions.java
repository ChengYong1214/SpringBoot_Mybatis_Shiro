package com.yong.demo.pojo;
/*
* 权限
*/
public class Permissions {
    private Integer id;
    private String permissionsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "id=" + id +
                ", permissionsName='" + permissionsName + '\'' +
                '}';
    }
}
