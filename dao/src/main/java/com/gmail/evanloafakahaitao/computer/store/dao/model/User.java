package com.gmail.evanloafakahaitao.computer.store.dao.model;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = "email"
))
@SQLDelete(sql = "update t_user set f_is_deleted = true where f_id = ?")
public class User extends SoftDeleteAndDisableEntity implements Serializable {

    private static final long serialVersionUID = -5535456587453610531L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column(nullable = false)
    @Size(max = 25)
    private String firstName;
    @NotNull
    @Column(nullable = false)
    @Size(max = 25)
    private String lastName;
    @NotNull
    @Email(message = "Not an email")
    @Column(unique = true, nullable = false)
    @Check(constraints = "f_email LIKE '%@%.%'")
    @Size(min = 5, max = 30)
    private String email;
    @NotNull
    @Column(nullable = false)
    @Size(min = 4, max = 30)
    private String password;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roleId")
    private Role role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Profile profile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discountId")
    private Discount discount;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userId", nullable = false)
    private Set<BusinessCard> businessCards = new HashSet<>();

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Set<BusinessCard> getBusinessCards() {
        return businessCards;
    }

    public void setBusinessCards(Set<BusinessCard> businessCards) {
        this.businessCards = businessCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!firstName.equals(user.firstName)) return false;
        return lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", profile=").append(profile);
        sb.append(", discount=").append(discount);
        sb.append(", businessCards=").append(businessCards);
        sb.append('}');
        return sb.toString();
    }
}
