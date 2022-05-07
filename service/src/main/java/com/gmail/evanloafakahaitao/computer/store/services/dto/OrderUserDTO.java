package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class OrderUserDTO {

    private String name;
    private String email;
    private ProfileDTO profile;
    private DiscountDTO discount;

    public OrderUserDTO() {}

    private OrderUserDTO(Builder builder) {
        setName(builder.name);
        setEmail(builder.email);
        setProfile(builder.profile);
        setDiscount(builder.discount);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        private String name;
        private String email;
        private ProfileDTO profile;
        private DiscountDTO discount;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
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

        public OrderUserDTO build() {
            return new OrderUserDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderUserDTO that = (OrderUserDTO) o;

        if (!name.equals(that.name)) return false;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderUserDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", profile=").append(profile);
        sb.append(", discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }
}
