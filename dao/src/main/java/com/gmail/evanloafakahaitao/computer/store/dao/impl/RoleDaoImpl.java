package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.RoleDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import org.hibernate.query.Query;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        String hql = "from Role as r where r.name = :name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }
}
