package com.gmail.evanloafakahaitao.computer.store.dao.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = -4038551219941151950L;
    @EmbeddedId
    private OrderId id = new OrderId();
    @NotNull
    @Column(nullable = false)
    @ColumnDefault(value = "NOW()")
    private LocalDateTime created;
    @NotNull
    @Column(nullable = false)
    private Integer quantity;
    @NotNull
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Formula("SELECT os.f_name FROM t_order_status os WHERE os.f_id = f_status_id")
    @Size(max = 20)
    private OrderStatusEnum status;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    private Item item;

    public Order() {}

    public OrderId getId() {
        return id;
    }

    public void setId(OrderId id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!id.equals(order.id)) return false;
        if (!created.equals(order.created)) return false;
        if (!quantity.equals(order.quantity)) return false;
        return totalPrice.equals(order.totalPrice);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + totalPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", quantity=").append(quantity);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", status=").append(status);
        sb.append(", user=").append(user);
        sb.append(", item=").append(item);
        sb.append('}');
        return sb.toString();
    }
}
