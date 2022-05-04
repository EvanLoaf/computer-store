package com.gmail.evanloafakahaitao.computer.store.services.dto;

import java.time.LocalDateTime;

public class DiscountDTO {

    private Long id;
    private String name;
    private Integer percent;
    private LocalDateTime finishDate;

    private DiscountDTO() {}

    private DiscountDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setPercent(builder.percent);
        setFinishDate(builder.finishDate);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private Integer percent;
        private LocalDateTime finishDate;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPercent(Integer percent) {
            this.percent = percent;
            return this;
        }

        public Builder withFinishDate(LocalDateTime finishDate) {
            this.finishDate = finishDate;
            return this;
        }

        public DiscountDTO build() {
            return new DiscountDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountDTO that = (DiscountDTO) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!percent.equals(that.percent)) return false;
        return finishDate.equals(that.finishDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + percent.hashCode();
        result = 31 * result + finishDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DiscountDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", percent=").append(percent);
        sb.append(", finishDate=").append(finishDate);
        sb.append('}');
        return sb.toString();
    }
}
