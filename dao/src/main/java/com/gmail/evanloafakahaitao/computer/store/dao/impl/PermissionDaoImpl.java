package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.PermissionDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {

    public PermissionDaoImpl() {
        super(Permission.class);
    }
}
