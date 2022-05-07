package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.OrderDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> findByUserId(Long id) {
        String hql = "FROM Order AS o WHERE o.user.id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Order findByOrderCode(String orderCode) {
        String hql = "FROM Order AS o WHERE o.orderCode = :orderCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderCode", orderCode);
        return (Order) query.getSingleResult();
    }

    @Override
    public void deleteByOrderCode(String orderCode) {
        String hql = "DELETE FROM Order AS o WHERE o.orderCode = :orderCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderCode", orderCode);
    }
}
