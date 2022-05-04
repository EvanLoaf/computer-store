package com.gmail.evanloafakahaitao.computer.store.services.dto;

public class FeedbackDTO {

    private Long id;
    private String message;
    private SimpleUserDTO user;

    private FeedbackDTO() {}

    private FeedbackDTO(Builder builder) {
        setId(builder.id);
        setMessage(builder.message);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SimpleUserDTO getUser() {
        return user;
    }

    public void setUser(SimpleUserDTO user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private String message;
        private SimpleUserDTO user;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withUser(SimpleUserDTO user) {
            this.user = user;
            return this;
        }

        public FeedbackDTO build() {
            return new FeedbackDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackDTO that = (FeedbackDTO) o;

        if (!id.equals(that.id)) return false;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FeedbackDTO{");
        sb.append("id=").append(id);
        sb.append(", message='").append(message).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
