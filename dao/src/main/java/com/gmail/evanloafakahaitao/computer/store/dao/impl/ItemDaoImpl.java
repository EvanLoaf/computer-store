package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public Item findByVendorCode(String vendorCode) {
        String hql = "FROM Item AS i WHERE i.vendorCode = :vendorCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("vendorCode", vendorCode);
        return (Item) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        String hql = "FROM Item AS i WHERE i.price BETWEEN :minPrice AND :maxPrice ORDER BY i.price DESC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findAllNotDeleted(Integer startPosition, Integer maxResults) {
        String hql = "FROM Item AS i WHERE i.isDeleted = false ORDER BY i.price ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.list();
    }
}
