package com.gmail.evanloafakahaitao.computer.store.dao.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class Comment implements Serializable {

    private static final long serialVersionUID = 5083307714018220177L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column(nullable = false)
    @Size(max = 300)
    private String content;
    @NotNull
    @Column(nullable = false)
    @ColumnDefault(value = "NOW()")
    private LocalDateTime created;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Comment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

        Comment comment = (Comment) o;

        if (!id.equals(comment.id)) return false;
        return created.equals(comment.created);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Comment{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", created=").append(created);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
