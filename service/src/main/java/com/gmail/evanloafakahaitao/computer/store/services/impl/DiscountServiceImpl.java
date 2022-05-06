package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DiscountDao;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.DiscountDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.services.DiscountService;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.dto.DiscountDTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private final DiscountDao discountDao = new DiscountDaoImpl();
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter = new DiscountDTOConverter();

    @Override
    public List<DiscountDTO> findAll() {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Discount> discounts = discountDao.findAll();
            List<DiscountDTO> discountsDTO = discountDTOConverter.toDTOList(discounts);
            transaction.commit();
            return discountsDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Discounts", e);
        }
        return Collections.emptyList();
    }
}
