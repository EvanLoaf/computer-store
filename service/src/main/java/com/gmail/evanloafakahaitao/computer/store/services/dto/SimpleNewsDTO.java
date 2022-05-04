package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class SimpleNewsDTO {

    private Long id;
    private String title;
    private String created;

    private SimpleNewsDTO() {}

    private SimpleNewsDTO(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setCreated(builder.created);
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public static final class Builder {
        private Long id;
        private String title;
        private String created;

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

        public Builder withCreated(String created) {
            this.created = created;
            return this;
        }

        public SimpleNewsDTO build() {
            return new SimpleNewsDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleNewsDTO that = (SimpleNewsDTO) o;

        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        return created.equals(that.created);
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
        final StringBuffer sb = new StringBuffer("SimpleNewsDTO{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", created='").append(created).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
