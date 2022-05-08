package com.gmail.evanloafakahaitao.computer.store.dao.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class SoftDeleteAndDisableEntity {

    @Column
    @NotNull
    private Boolean isDeleted;
    @Column
    @NotNull
    private Boolean isDisabled;

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
    }
}
