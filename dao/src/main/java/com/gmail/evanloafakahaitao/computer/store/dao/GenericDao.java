package com.gmail.evanloafakahaitao.computer.store.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

    T findOne(final long entityId);

    List<T> findAll();

    List<T> findAll(Integer firstResult, Integer maxResults);

    Long countAll();

    Long countAllNotDeleted();

    void create(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);
}
