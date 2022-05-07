package com.gmail.evanloafakahaitao.computer.store.services.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ItemDTO {

    private Long id;
    private String name;
    private String vendorCode;
    private String description;
    private BigDecimal price;
    private Set<DiscountDTO> discounts = new HashSet<>();

    public ItemDTO() {}

    private ItemDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setVendorCode(builder.vendorCode);
        setDescription(builder.description);
        setPrice(builder.price);
        setDiscounts(builder.discounts);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<DiscountDTO> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<DiscountDTO> discounts) {
        this.discounts = discounts;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String vendorCode;
        private String description;
        private BigDecimal price;
        private Set<DiscountDTO> discounts;

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

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withDiscounts(Set<DiscountDTO> discounts) {
            this.discounts = discounts;
            return this;
        }

        public ItemDTO build() {
            return new ItemDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDTO itemDTO = (ItemDTO) o;

        if (!id.equals(itemDTO.id)) return false;
        if (!vendorCode.equals(itemDTO.vendorCode)) return false;
        return price.equals(itemDTO.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + vendorCode.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", vendorCode='").append(vendorCode).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", discounts=").append(discounts);
        sb.append('}');
        return sb.toString();
    }
}
