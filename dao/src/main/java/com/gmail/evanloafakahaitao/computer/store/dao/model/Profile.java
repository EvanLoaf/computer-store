package com.gmail.evanloafakahaitao.computer.store.dao.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table
public class Profile implements Serializable {

    private static final long serialVersionUID = 5464403147998864722L;
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user")
    )
    @Id
    @GeneratedValue(generator = "generator")
    @Column(unique = true, updatable = false, nullable = false)
    private Long userId;
    @Column
    @Size(max = 100)
    private String address;
    @Column
    @Size(max = 15)
    private String phoneNumber;
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public Profile() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return userId.equals(profile.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Profile{");
        sb.append("userId=").append(userId);
        sb.append(", address='").append(address).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
