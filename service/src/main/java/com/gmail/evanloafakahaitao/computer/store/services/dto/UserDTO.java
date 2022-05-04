package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleDTO role;
    private ProfileDTO profile;
    private DiscountDTO discount;

    private UserDTO() {}

    private UserDTO(Builder builder) {
        setId(builder.id);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setEmail(builder.email);
        setPassword(builder.password);
        setRole(builder.role);
        setProfile(builder.profile);
        setDiscount(builder.discount);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private RoleDTO role;
        private ProfileDTO profile;
        private DiscountDTO discount;

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

        public Builder withRole(RoleDTO role) {
            this.role = role;
            return this;
        }

        public Builder withProfile(ProfileDTO profile) {
            this.profile = profile;
            return this;
        }

        public Builder withDiscount(DiscountDTO discount) {
            this.discount = discount;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (!id.equals(userDTO.id)) return false;
        if (!firstName.equals(userDTO.firstName)) return false;
        if (!lastName.equals(userDTO.lastName)) return false;
        return email.equals(userDTO.email);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserDTO{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", profile=").append(profile);
        sb.append(", discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }
}
