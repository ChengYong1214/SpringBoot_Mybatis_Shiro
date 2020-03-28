package com.yong.demo.pojo;

import java.util.Set;

/*
* 角色
*/
public class Role {
    private Integer id;
    private String roleName;
    private Set<Permissions> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + roleName + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
