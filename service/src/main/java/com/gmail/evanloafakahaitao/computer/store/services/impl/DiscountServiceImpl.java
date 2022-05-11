package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DiscountDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.services.DiscountService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
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
    @Transactional(readOnly = true)
    public List<DiscountDTO> findAll() {
        logger.info("Retrieving all Discounts");
        List<Discount> discounts = discountDao.findAll();
        logger.debug("Retrieved all discounts : {}", discounts);
        if (!discounts.isEmpty()) {
            return discountDTOConverter.toDTOList(discounts);
        } else return Collections.emptyList();
    }
}
