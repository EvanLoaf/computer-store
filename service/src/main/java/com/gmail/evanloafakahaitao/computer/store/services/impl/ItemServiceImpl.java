package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DiscountDao;
import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemDao itemDao;
    private final DiscountDao discountDao;
    private final EntityConverter<ItemDTO, Item> itemEntityConverter;
    private final EntityConverter<DiscountDTO, Discount> discountEntityConverter;
    private final DTOConverter<ItemDTO, Item> itemDTOConverter;

    @Autowired
    public ItemServiceImpl(
            ItemDao itemDao,
            DiscountDao discountDao,
            @Qualifier("itemEntityConverter") EntityConverter<ItemDTO, Item> itemEntityConverter,
            @Qualifier("discountEntityConverter") EntityConverter<DiscountDTO, Discount> discountEntityConverter,
            @Qualifier("itemDTOConverter") DTOConverter<ItemDTO, Item> itemDTOConverter
    ) {
        this.itemDao = itemDao;
        this.discountDao = discountDao;
        this.itemEntityConverter = itemEntityConverter;
        this.discountEntityConverter = discountEntityConverter;
        this.itemDTOConverter = itemDTOConverter;
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemEntityConverter.toEntity(itemDTO);
            itemDao.create(item);
            ItemDTO savedItem = itemDTOConverter.toDto(item);
            transaction.commit();
            return savedItem;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Item", e);
        }
        return null;
    }

    @Override
    public List<ItemDTO> save(List<ItemDTO> itemDTOList) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = new ArrayList<>();
            for (ItemDTO itemDTO : itemDTOList) {
                Item item = itemEntityConverter.toEntity(itemDTO);
                itemDao.create(item);
                items.add(item);
            }
            List<ItemDTO> savedItems = itemDTOConverter.toDTOList(items);
            transaction.commit();
            return savedItems;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save list of Items", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ItemDTO> findAll() {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findAll();
            List<ItemDTO> foundItems = itemDTOConverter.toDTOList(items);
            transaction.commit();
            return foundItems;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Items", e);
        }
        return Collections.emptyList();
    }

    @Override
    public ItemDTO findByVendorCode(SimpleItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemDao.findByVendorCode(itemDTO.getVendorCode());
            ItemDTO foundItem = itemDTOConverter.toDto(item);
            transaction.commit();
            return foundItem;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Item by vendor code", e);
        }
        return null;
    }

    @Override
    public ItemDTO findById(SimpleItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemDao.findOne(itemDTO.getId());
            ItemDTO foundItem = itemDTOConverter.toDto(item);
            transaction.commit();
            return foundItem;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Item by id", e);
        }
        return null;
    }
}
