package com.gmail.evanloafakahaitao.computer.store.controllers.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class APIResponse implements Serializable {

    private static final long serialVersionUID = 8179165286672291619L;

    private String message;
    private Set<String> errors = new HashSet<>();

    public APIResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        APIResponse that = (APIResponse) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return errors != null ? errors.equals(that.errors) : that.errors == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("APIResponse{");
        sb.append("message='").append(message).append('\'');
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }
}
