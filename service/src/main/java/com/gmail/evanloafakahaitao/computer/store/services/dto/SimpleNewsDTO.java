package com.gmail.evanloafakahaitao.computer.store.services.dto;

import java.time.LocalDateTime;

public class SimpleNewsDTO {

    private Long id;
    private String title;
    private LocalDateTime created;

    public SimpleNewsDTO() {}

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
