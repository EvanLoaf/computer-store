package com.gmail.evanloafakahaitao.computer.store.servlets.model;

import com.gmail.evanloafakahaitao.computer.store.dao.model.RoleEnum;

import java.util.Objects;

public class AccessMode {

    private RoleEnum role;
    private CommandEnum command;
    private RequestMethodEnum request;

    private AccessMode(Builder builder) {
        role = builder.role;
        command = builder.command;
        request = builder.request;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public RoleEnum getRole() {
        return role;
    }

    public CommandEnum getCommand() {
        return command;
    }

    public RequestMethodEnum getRequest() {
        return request;
    }

    public static final class Builder {
        private RoleEnum role;
        private CommandEnum command;
        private RequestMethodEnum request;

        private Builder() {
        }

        public Builder withRole(RoleEnum role) {
            this.role = role;
            return this;
        }

        public Builder withCommand(CommandEnum command) {
            this.command = command;
            return this;
        }

        public Builder withRequest(RequestMethodEnum request) {
            this.request = request;
            return this;
        }

        public AccessMode build() {
            return new AccessMode(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessMode that = (AccessMode) o;
        return role == that.role &&
                command == that.command &&
                request == that.request;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, command, request);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AccessMode{");
        sb.append("role=").append(role);
        sb.append(", command=").append(command);
        sb.append(", request=").append(request);
        sb.append('}');
        return sb.toString();
    }
}
