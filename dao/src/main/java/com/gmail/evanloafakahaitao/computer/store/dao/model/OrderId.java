package com.gmail.evanloafakahaitao.computer.store.dao.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class OrderId implements Serializable {

    private static final long serialVersionUID = 5354790978873740236L;
    @NotNull
    @Column(nullable = false, updatable = false)
    private Long userId;
    @NotNull
    @Column(nullable = false, updatable = false)
    private Long itemId;
    @NotNull
    @Column(nullable = false, updatable = false, unique = true, columnDefinition = "char")
    @Size(min = 36, max = 36)
    private String orderCode;

    public OrderId() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderId orderId = (OrderId) o;

        if (!userId.equals(orderId.userId)) return false;
        if (!itemId.equals(orderId.itemId)) return false;
        return orderCode.equals(orderId.orderCode);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + itemId.hashCode();
        result = 31 * result + orderCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderId{");
        sb.append("userId=").append(userId);
        sb.append(", itemId=").append(itemId);
        sb.append(", orderCode='").append(orderCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
