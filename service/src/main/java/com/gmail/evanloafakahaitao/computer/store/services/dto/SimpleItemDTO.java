package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class SimpleItemDTO {

    private Long id;
    private String name;
    private String vendorCode;

    public SimpleItemDTO() {}

    private SimpleItemDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setVendorCode(builder.vendorCode);
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

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String vendorCode;

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

        public Builder withVendorCode(String vendorCode) {
            this.vendorCode = vendorCode;
            return this;
        }

        public SimpleItemDTO build() {
            return new SimpleItemDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleItemDTO that = (SimpleItemDTO) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return vendorCode.equals(that.vendorCode);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + vendorCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SimpleItemDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", vendorCode='").append(vendorCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
