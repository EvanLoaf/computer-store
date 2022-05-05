package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class PermissionDTO {

    private Long id;
    private PermissionEnum name;

    public PermissionDTO() {}

    private PermissionDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
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

    public PermissionEnum getName() {
        return name;
    }

    public void setName(PermissionEnum name) {
        this.name = name;
    }

    public static final class Builder {
        private Long id;
        private PermissionEnum name;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(PermissionEnum name) {
            this.name = name;
            return this;
        }

        public PermissionDTO build() {
            return new PermissionDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionDTO that = (PermissionDTO) o;

        if (!id.equals(that.id)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PermissionDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
