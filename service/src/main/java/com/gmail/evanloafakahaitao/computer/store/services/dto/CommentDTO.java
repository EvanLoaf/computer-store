package com.gmail.evanloafakahaitao.computer.store.services.dto;

import java.time.LocalDateTime;

public class CommentDTO {

    private Long id;
    private String content;
    private LocalDateTime created;
    private SimpleUserDTO user;

    public CommentDTO() {}

    private CommentDTO(Builder builder) {
        setId(builder.id);
        setContent(builder.content);
        setCreated(builder.created);
        setUser(builder.user);
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

    public static final class Builder {
        private Long id;
        private String content;
        private LocalDateTime created;
        private SimpleUserDTO user;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
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

        public CommentDTO build() {
            return new CommentDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentDTO that = (CommentDTO) o;

        if (!id.equals(that.id)) return false;
        return created.equals(that.created);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CommentDTO{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", created=").append(created);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
