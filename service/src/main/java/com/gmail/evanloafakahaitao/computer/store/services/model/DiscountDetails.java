package com.gmail.evanloafakahaitao.computer.store.services.model;

import java.math.BigDecimal;

public class ItemDiscount {

    private Long discountId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    private ItemDiscount(Builder builder) {
        discountId = builder.discountId;
        minPrice = builder.minPrice;
        maxPrice = builder.maxPrice;
    }

    private ItemDiscount() {}

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long discountId;
        private BigDecimal minPrice;
        private BigDecimal maxPrice;

        private Builder() {
        }

        public Builder withDiscountId(Long discountId) {
            this.discountId = discountId;
            return this;
        }

        public Builder withMinPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public Builder withMaxPrice(BigDecimal maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public ItemDiscount build() {
            return new ItemDiscount(this);
        }
    }

    public Long getDiscountId() {
        return discountId;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDiscount that = (ItemDiscount) o;

        if (!discountId.equals(that.discountId)) return false;
        if (!minPrice.equals(that.minPrice)) return false;
        return maxPrice.equals(that.maxPrice);
    }

    @Override
    public int hashCode() {
        int result = discountId.hashCode();
        result = 31 * result + minPrice.hashCode();
        result = 31 * result + maxPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemDiscount{");
        sb.append("discountId=").append(discountId);
        sb.append(", minPrice=").append(minPrice);
        sb.append(", maxPrice=").append(maxPrice);
        sb.append('}');
        return sb.toString();
    }
}
