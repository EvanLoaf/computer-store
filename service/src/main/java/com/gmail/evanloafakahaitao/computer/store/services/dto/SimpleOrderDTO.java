package com.gmail.evanloafakahaitao.computer.store.services.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SimpleOrderDTO {

    private String orderCode;
    private LocalDateTime created;
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatusEnum status;
    private SimpleItemDTO item;

    public SimpleOrderDTO() {}

    private SimpleOrderDTO(Builder builder) {
        setOrderCode(builder.orderCode);
        setCreated(builder.created);
        setQuantity(builder.quantity);
        setTotalPrice(builder.totalPrice);
        setStatus(builder.status);
        setItem(builder.item);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public SimpleItemDTO getItem() {
        return item;
    }

    public void setItem(SimpleItemDTO item) {
        this.item = item;
    }

    public static final class Builder {
        private String orderCode;
        private LocalDateTime created;
        private Integer quantity;
        private BigDecimal totalPrice;
        private OrderStatusEnum status;
        private SimpleItemDTO item;

        private Builder() {
        }

        public Builder withOrderCode(String orderCode) {
            this.orderCode = orderCode;
            return this;
        }

        public Builder withCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder withStatus(OrderStatusEnum status) {
            this.status = status;
            return this;
        }

        public Builder withItem(SimpleItemDTO item) {
            this.item = item;
            return this;
        }

        public SimpleOrderDTO build() {
            return new SimpleOrderDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleOrderDTO that = (SimpleOrderDTO) o;

        if (!orderCode.equals(that.orderCode)) return false;
        if (!created.equals(that.created)) return false;
        if (!quantity.equals(that.quantity)) return false;
        return totalPrice.equals(that.totalPrice);
    }

    @Override
    public int hashCode() {
        int result = orderCode.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + totalPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SimpleOrderDTO{");
        sb.append("orderCode='").append(orderCode).append('\'');
        sb.append(", created=").append(created);
        sb.append(", quantity=").append(quantity);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", status=").append(status);
        sb.append(", item=").append(item);
        sb.append('}');
        return sb.toString();
    }
}
