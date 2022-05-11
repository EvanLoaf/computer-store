package com.gmail.evanloafakahaitao.computer.store.services.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class NewsDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private SimpleUserDTO user;
    private Set<CommentDTO> comments = new HashSet<>();

    public NewsDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public SimpleUserDTO getUser() {
        return user;
    }

    public void setUser(SimpleUserDTO user) {
        this.user = user;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDTO newsDTO = (NewsDTO) o;

        if (id != null ? !id.equals(newsDTO.id) : newsDTO.id != null) return false;
        if (!title.equals(newsDTO.title)) return false;
        return created != null ? created.equals(newsDTO.created) : newsDTO.created == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NewsDTO{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", created=").append(created);
        sb.append(", user=").append(user);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }
}
