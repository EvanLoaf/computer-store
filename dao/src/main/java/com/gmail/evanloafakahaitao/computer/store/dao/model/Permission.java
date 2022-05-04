package com.gmail.evanloafakahaitao.computer.store.dao.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Permission implements Serializable {

    private static final long serialVersionUID = 2854611344221044150L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    @Size(max = 30)
    private PermissionEnum name;

    public Permission() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionEnum getName() {
        return name;
    }

    public void setName(PermissionEnum name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (!id.equals(that.id)) return false;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Permission{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
}
