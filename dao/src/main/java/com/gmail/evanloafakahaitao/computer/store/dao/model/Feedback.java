package com.gmail.evanloafakahaitao.computer.store.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table
public class Feedback implements Serializable {

    private static final long serialVersionUID = -1051662317473305715L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column(nullable = false)
    @Size(max = 200)
    private String message;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, updatable = false)
    private User user;

    public Feedback() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

        Feedback feedback = (Feedback) o;

        if (!id.equals(feedback.id)) return false;
        return message.equals(feedback.message);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Feedback{");
        sb.append("id=").append(id);
        sb.append(", message='").append(message).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
