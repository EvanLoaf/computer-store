package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T findOne(long entityId) {
        return getCurrentSession().get(clazz, entityId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("FROM " + clazz.getSimpleName()).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Integer firstResult, Integer maxResults) {
        Query query = getCurrentSession().createQuery("FROM " + clazz.getSimpleName());
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();
    }

    @Override
    public Long countAll() {
        return (Long) getCurrentSession().createQuery("SELECT COUNT(*) FROM " + clazz.getSimpleName()).uniqueResult();
    }

    @Override
    public Long countAllNotDeleted() {
        return (Long) getCurrentSession().createQuery("SELECT COUNT(*) FROM " + clazz.getSimpleName() + " AS c WHERE c.isDeleted = false").uniqueResult();
    }

    @Override
    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
