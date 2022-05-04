package com.gmail.evanloafakahaitao.computer.store.dao.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Role implements Serializable {

    private static final long serialVersionUID = -2664612401417123082L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    @Size(max = 20)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "authorization",
            joinColumns = @JoinColumn(name = "roleId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "permissionId", nullable = false, updatable = false)
    )
    private Set<Permission> permissions = new HashSet<>();

    public Role() {}

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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!id.equals(role.id)) return false;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", permissions=").append(permissions);
        sb.append('}');
        return sb.toString();
    }
}
