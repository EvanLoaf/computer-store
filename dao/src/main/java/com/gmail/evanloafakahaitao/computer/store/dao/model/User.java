package com.gmail.evanloafakahaitao.computer.store.dao.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String additionalInfo;
    private String phoneNumber;
    private RoleEnum role;
    private List<Order> orders;

    private User(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        password = builder.password;
        additionalInfo = builder.additionalInfo;
        phoneNumber = builder.phoneNumber;
        role = builder.role;
        orders = builder.orders;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RoleEnum getRole() {
        return role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String additionalInfo;
        private String phoneNumber;
        private RoleEnum role;
        private List<Order> orders;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withAdditionalInfo(String additionalInfo) {
            this.additionalInfo = additionalInfo;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withRole(RoleEnum role) {
            this.role = role;
            return this;
        }

        public Builder withOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", additionalInfo='").append(additionalInfo).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", role=").append(role);
        sb.append(", orders=").append(orders);
        sb.append('}');
        return sb.toString();
    }
}
