package com.gmail.evanloafakahaitao.computer.store.dao;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role findDefault();
}
