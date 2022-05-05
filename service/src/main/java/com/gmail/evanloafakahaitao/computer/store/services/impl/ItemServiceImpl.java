package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DiscountDao;
import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.DiscountDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.dto.ItemDTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.entity.DiscountEntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.entity.ItemEntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.ItemXmlDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemDao itemDao = new ItemDaoImpl();
    private final DiscountDao discountDao = new DiscountDaoImpl();
    private final EntityConverter<ItemDTO, Item> itemEntityConverter = new ItemEntityConverter();
    private final EntityConverter<DiscountDTO, Discount> discountEntityConverter = new DiscountEntityConverter();
    private final DTOConverter<ItemDTO, Item> itemDTOConverter = new ItemDTOConverter();

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
            transaction.commit();
            return itemDTOConverter.toDto(item);
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
            List<Item> savedItems = new ArrayList<>();
            for (ItemDTO itemDTO : itemDTOList) {
                Item item = itemEntityConverter.toEntity(itemDTO);
                itemDao.create(item);
                savedItems.add(item);
            }
            transaction.commit();
            return itemDTOConverter.toDTOList(savedItems);
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
            transaction.commit();
            return itemDTOConverter.toDTOList(items);
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
            transaction.commit();
            return itemDTOConverter.toDto(item);
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
            transaction.commit();
            return itemDTOConverter.toDto(item);
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Item by id", e);
        }
        return null;
    }
}
