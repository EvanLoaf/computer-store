package com.gmail.evanloafakahaitao.computer.store.services.dto;

import java.util.HashSet;
import java.util.Set;

public class RoleDTO {

    private Long id;
    private String name;
    private Set<PermissionDTO> permissions = new HashSet<>();

    public RoleDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDTO roleDTO = (RoleDTO) o;

        if (id != null ? !id.equals(roleDTO.id) : roleDTO.id != null) return false;
        return name.equals(roleDTO.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RoleDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", permissions=").append(permissions);
        sb.append('}');
        return sb.toString();
    }
}
