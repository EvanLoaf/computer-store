package com.gmail.evanloafakahaitao.computer.store.servlets.model;

import com.gmail.evanloafakahaitao.computer.store.dao.model.RoleEnum;

import java.io.Serializable;
import java.util.Objects;

public class UserPrincipal implements Serializable {

    private Long id;
    private String name;
    private String email;
    private RoleEnum role;

    private UserPrincipal(Builder builder) {
        id = builder.id;
        name = builder.name;
        email = builder.email;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public RoleEnum getRole() {
        return role;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String email;
        private RoleEnum role;

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

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withRole(RoleEnum role) {
            this.role = role;
            return this;
        }

        public UserPrincipal build() {
            return new UserPrincipal(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
