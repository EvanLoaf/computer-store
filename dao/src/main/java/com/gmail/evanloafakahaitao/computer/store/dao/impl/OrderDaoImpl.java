package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.OrderDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> findByUserId(Long id, Integer firstResult, Integer maxResults) {
        String hql = "FROM Order AS o WHERE o.user.id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();
    }

    @Override
    public Order findByOrderCode(String orderCode) {
        //TODO possibly o.id.orderCode
        String hql = "FROM Order AS o WHERE o.orderCode = :orderCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderCode", orderCode);
        return (Order) query.uniqueResult();
    }

    @Override
    public void deleteByOrderCode(String orderCode) {
        String hql = "DELETE FROM Order AS o WHERE o.orderCode = :orderCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderCode", orderCode);
    }

    @Override
    public Long countAllByUserId(Long id) {
        String hql = "SELECT COUNT(*) FROM Order AS o WHERE o.user.id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Long) query.uniqueResult();
    }
}
