package com.gmail.evanloafakahaitao.computer.store.services.model;

import java.math.BigDecimal;

public class DiscountDetails {

    private Long discountId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public DiscountDetails() {}

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
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

        DiscountDetails that = (DiscountDetails) o;

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
        final StringBuffer sb = new StringBuffer("DiscountDetails{");
        sb.append("discountId=").append(discountId);
        sb.append(", minPrice=").append(minPrice);
        sb.append(", maxPrice=").append(maxPrice);
        sb.append('}');
        return sb.toString();
    }
}
