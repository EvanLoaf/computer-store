package com.gmail.evanloafakahaitao.computer.store.dao.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class Discount implements Serializable {

    private static final long serialVersionUID = 8286982232288656270L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column(nullable = false)
    @Size(max = 20)
    private String name;
    @NotNull
    @Column(nullable = false)
    private Integer percent;
    @NotNull
    @Column(nullable = false)
    @ColumnDefault(value = "NOW() + INTERVAL 14 DAY")
    private LocalDateTime finishDate;

    public Discount() {}

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

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (id != null ? !id.equals(discount.id) : discount.id != null) return false;
        if (name != null ? !name.equals(discount.name) : discount.name != null) return false;
        return percent != null ? percent.equals(discount.percent) : discount.percent == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Discount{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", percent=").append(percent);
        sb.append(", finishDate=").append(finishDate);
        sb.append('}');
        return sb.toString();
    }
}
