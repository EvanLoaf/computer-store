package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DiscountDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.services.DiscountService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private final DiscountDao discountDao;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;

    @Autowired
    public DiscountServiceImpl(
            DiscountDao discountDao,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter
    ) {
        this.discountDao = discountDao;
        this.discountDTOConverter = discountDTOConverter;
    }

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
