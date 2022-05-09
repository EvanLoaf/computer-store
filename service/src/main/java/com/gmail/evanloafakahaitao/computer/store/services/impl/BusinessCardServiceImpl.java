package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.BusinessCardDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.BusinessCard;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.BusinessCardService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.BusinessCardDTO;
import com.gmail.evanloafakahaitao.computer.store.services.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessCardServiceImpl implements BusinessCardService {

    private static final Logger logger = LogManager.getLogger(BusinessCardServiceImpl.class);

    private final BusinessCardDao businessCardDao;
    private final UserDao userDao;
    private final DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter;
    private final EntityConverter<BusinessCardDTO, BusinessCard> businessCardEntityConverter;

    public BusinessCardServiceImpl(
            BusinessCardDao businessCardDao,
            UserDao userDao,
            @Qualifier("businessCardDTOConverter") DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter,
            @Qualifier("businessCardEntityConverter") EntityConverter<BusinessCardDTO, BusinessCard> businessCardEntityConverter
    ) {
        this.businessCardDao = businessCardDao;
        this.userDao = userDao;
        this.businessCardDTOConverter = businessCardDTOConverter;
        this.businessCardEntityConverter = businessCardEntityConverter;
    }

    @Override
    public BusinessCardDTO save(BusinessCardDTO businessCard) {
        logger.info("Saving Business Card");
        logger.debug("Saving Business Card : {}", businessCard);
        User user = userDao.findOne(
                CurrentUserUtil.getCurrentId()
        );
        BusinessCard newBusinessCard = businessCardEntityConverter.toEntity(businessCard);
        user.getBusinessCards().add(newBusinessCard);
        userDao.update(user);
        return businessCardDTOConverter.toDto(newBusinessCard);
    }

    @Override
    public void deleteById(BusinessCardDTO businessCard) {
        logger.info("Deleting Business Card by Id");
        logger.debug("Deleting Business Card by Id : {}", businessCard.getId());
        businessCardDao.deleteById(businessCard.getId());
    }
}
