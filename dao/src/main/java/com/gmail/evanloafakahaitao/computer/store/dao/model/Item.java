package com.gmail.evanloafakahaitao.computer.store.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Item implements Serializable {

    private Long id;
    private String name;
    private String vendorCode;
    private String description;
    private BigDecimal price;
    private List<Order> ordersForItem;

    private Item(Builder builder) {
        id = builder.id;
        name = builder.name;
        vendorCode = builder.vendorCode;
        description = builder.description;
        price = builder.price;
        ordersForItem = builder.ordersForItem;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Order> getOrdersForItem() {
        return ordersForItem;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String vendorCode;
        private String description;
        private BigDecimal price;
        private List<Order> ordersForItem;

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

        public Builder withOrdersForItem(List<Order> ordersForItem) {
            this.ordersForItem = ordersForItem;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(vendorCode, item.vendorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vendorCode);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", vendorCode=").append(vendorCode);
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", ordersForItem=").append(ordersForItem);
        sb.append('}');
        return sb.toString();
    }
}
