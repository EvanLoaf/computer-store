package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class NewOrderDTO {

    private Integer quantity;
    private SimpleUserDTO user;
    private SimpleItemDTO item;

    private NewOrderDTO() {}

    private NewOrderDTO(Builder builder) {
        setQuantity(builder.quantity);
        setUser(builder.user);
        setItem(builder.item);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SimpleUserDTO getUser() {
        return user;
    }

    public void setUser(SimpleUserDTO user) {
        this.user = user;
    }

    public SimpleItemDTO getItem() {
        return item;
    }

    public void setItem(SimpleItemDTO item) {
        this.item = item;
    }

    public static final class Builder {
        private Integer quantity;
        private SimpleUserDTO user;
        private SimpleItemDTO item;

        private Builder() {
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withUser(SimpleUserDTO user) {
            this.user = user;
            return this;
        }

        public Builder withItem(SimpleItemDTO item) {
            this.item = item;
            return this;
        }

        public NewOrderDTO build() {
            return new NewOrderDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewOrderDTO that = (NewOrderDTO) o;

        return quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return quantity.hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NewOrderDTO{");
        sb.append("quantity=").append(quantity);
        sb.append(", user=").append(user);
        sb.append(", item=").append(item);
        sb.append('}');
        return sb.toString();
    }
}
