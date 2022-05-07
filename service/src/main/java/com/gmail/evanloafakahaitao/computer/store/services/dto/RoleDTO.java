package com.gmail.evanloafakahaitao.computer.store.services.dto;

import java.util.HashSet;
import java.util.Set;

public class RoleDTO {

    private Long id;
    private String name;
    private Set<PermissionDTO> permissions = new HashSet<>();

    public RoleDTO() {}

    private RoleDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setPermissions(builder.permissions);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    public static final class Builder {
        private Long id;
        private String name;
        private Set<PermissionDTO> permissions;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPermissions(Set<PermissionDTO> permissions) {
            this.permissions = permissions;
            return this;
        }

        public RoleDTO build() {
            return new RoleDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDTO roleDTO = (RoleDTO) o;

        if (!id.equals(roleDTO.id)) return false;
        return name.equals(roleDTO.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
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
