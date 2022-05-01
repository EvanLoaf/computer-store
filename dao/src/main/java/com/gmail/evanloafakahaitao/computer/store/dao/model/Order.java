package com.gmail.evanloafakahaitao.computer.store.dao.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order implements Serializable {

    private Long id;
    private String orderCode;
    private LocalDateTime created;
    private Integer quantity;
    private Item item;
    private User user;

    private Order(Builder builder) {
        id = builder.id;
        orderCode = builder.orderCode;
        created = builder.created;
        quantity = builder.quantity;
        item = builder.item;
        user = builder.user;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public User getUser() {
        return user;
    }

    public static final class Builder {
        private Long id;
        private String orderCode;
        private LocalDateTime created;
        private Integer quantity;
        private Item item;
        private User user;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
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

        public Builder withItem(Item item) {
            this.item = item;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderCode, order.orderCode) &&
                Objects.equals(created, order.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderCode, created);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("id=").append(id);
        sb.append(", orderCode='").append(orderCode).append('\'');
        sb.append(", created=").append(created);
        sb.append(", quantity=").append(quantity);
        sb.append(", item=").append(item);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
