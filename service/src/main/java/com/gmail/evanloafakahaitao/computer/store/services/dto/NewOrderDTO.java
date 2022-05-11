package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class NewOrderDTO {

    private Integer quantity;
    private SimpleUserDTO user;
    private SimpleItemDTO item;

    public NewOrderDTO() {}

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
