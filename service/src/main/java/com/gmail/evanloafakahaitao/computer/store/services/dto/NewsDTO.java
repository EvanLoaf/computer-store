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

    private NewsDTO() {}

    private NewsDTO(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setContent(builder.content);
        setCreated(builder.created);
        setUser(builder.user);
        setComments(builder.comments);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    public static final class Builder {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime created;
        private SimpleUserDTO user;
        private Set<CommentDTO> comments;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public Builder withUser(SimpleUserDTO user) {
            this.user = user;
            return this;
        }

        public Builder withComments(Set<CommentDTO> comments) {
            this.comments = comments;
            return this;
        }

        public NewsDTO build() {
            return new NewsDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDTO newsDTO = (NewsDTO) o;

        if (!id.equals(newsDTO.id)) return false;
        if (!title.equals(newsDTO.title)) return false;
        return created.equals(newsDTO.created);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + created.hashCode();
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
