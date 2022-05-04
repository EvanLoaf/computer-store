package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class ProfileDTO {

    private String address;
    private String phoneNumber;

    private ProfileDTO() {}

    private ProfileDTO(Builder builder) {
        setAddress(builder.address);
        setPhoneNumber(builder.phoneNumber);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static final class Builder {
        private String address;
        private String phoneNumber;

        private Builder() {
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ProfileDTO build() {
            return new ProfileDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileDTO that = (ProfileDTO) o;

        if (!address.equals(that.address)) return false;
        return phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = address.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProfileDTO{");
        sb.append("address='").append(address).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
