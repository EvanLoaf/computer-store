package com.gmail.evanloafakahaitao.computer.store.dao.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item implements Serializable {

    private static final long serialVersionUID = 2259446884247522586L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column(nullable = false)
    @Size(max = 50)
    private String name;
    @NotNull
    @Column(nullable = false, unique = true)
    @Size(min = 10, max = 10)
    private String vendorCode;
    @Column
    @Size(max = 100)
    private String description;
    @NotNull
    @Column(nullable = false)
    @Check(constraints = "f_price >= 199.99")
    private BigDecimal price;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "promotion",
            joinColumns = @JoinColumn(name = "itemId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "discountId", nullable = false, updatable = false)
    )
    private Set<Discount> discounts = new HashSet<>();

    public Item() {}

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

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!id.equals(item.id)) return false;
        return vendorCode.equals(item.vendorCode);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + vendorCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
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
