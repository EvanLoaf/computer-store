package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DiscountDao;
import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.model.DiscountDetails;
import com.gmail.evanloafakahaitao.computer.store.services.properties.XmlProperties;
import com.gmail.evanloafakahaitao.computer.store.services.util.FileConverter;
import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.ItemXmlDTO;
import com.gmail.evanloafakahaitao.computer.store.services.xml.util.ItemXmlConverter;
import com.gmail.evanloafakahaitao.computer.store.services.xml.util.XmlParser;
import com.gmail.evanloafakahaitao.computer.store.services.xml.util.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemDao itemDao;
    private final DiscountDao discountDao;
    private final EntityConverter<ItemDTO, Item> itemEntityConverter;
    private final DTOConverter<ItemDTO, Item> itemDTOConverter;
    private final FileConverter fileConverter;
    private final ItemXmlConverter itemXmlConverter;
    private final XmlParser xmlParser;
    private final XmlValidator xmlValidator;
    private final XmlProperties xmlProperties;

    @Autowired
    public ItemServiceImpl(
            ItemDao itemDao,
            DiscountDao discountDao,
            @Qualifier("itemEntityConverter") EntityConverter<ItemDTO, Item> itemEntityConverter,
            @Qualifier("itemDTOConverter") DTOConverter<ItemDTO, Item> itemDTOConverter,
            FileConverter fileConverter,
            ItemXmlConverter itemXmlConverter,
            XmlParser xmlParser,
            XmlValidator xmlValidator,
            XmlProperties xmlProperties
    ) {
        this.itemDao = itemDao;
        this.discountDao = discountDao;
        this.itemEntityConverter = itemEntityConverter;
        this.itemDTOConverter = itemDTOConverter;
        this.fileConverter = fileConverter;
        this.itemXmlConverter = itemXmlConverter;
        this.xmlParser = xmlParser;
        this.xmlValidator = xmlValidator;
        this.xmlProperties = xmlProperties;
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        logger.info("Saving Item");
        logger.debug("Saving Item : {}", itemDTO);
        Item item = itemEntityConverter.toEntity(itemDTO);
        item.setDeleted(false);
        itemDao.create(item);
        return itemDTOConverter.toDto(item);
    }

    @Override
    public List<String> save(MultipartFile multipartFile) {
        logger.info("Retrieving XML Items from uploaded File");
        List<ItemXmlDTO> xmlItemsList = Collections.emptyList();
        try {
            File itemsFile = fileConverter.convert(multipartFile);
            boolean validation = xmlValidator.validate(itemsFile, xmlProperties.getSchemaFilePath());
            logger.info("Catalog XML is Valid : {}", validation);
            if (validation) {
                logger.info("Parsing XML Catalog");
                xmlItemsList = xmlParser.unmarshal(itemsFile).getItems();
            } else return Collections.emptyList();
        } catch (IOException e) {
            logger.error("Error converting MultipartFile to File", e);
        }
        List<ItemDTO> itemDTOList = itemXmlConverter.toItems(xmlItemsList);
        List<String> itemVendorCodeDuplicates = new ArrayList<>();
        itemDTOList.stream()
                .filter(itemDTO -> {
                    Item item = itemDao.findByVendorCode(itemDTO.getVendorCode());
                    if (item != null) {
                        itemVendorCodeDuplicates.add(itemDTO.getVendorCode());
                        logger.debug("Found duplicate vendor code value while saving Item : {}", itemDTO.getVendorCode());
                        return false;
                    } else return true;
                })
                .map(itemEntityConverter::toEntity)
                .forEach(
                        ((Consumer<Item>) item -> item.setDeleted(false))
                                .andThen(itemDao::create)
                );
        return itemVendorCodeDuplicates;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemDTO> findAllNotDeleted(Integer firstResult, Integer maxResults) {
        logger.info("Retrieving Items");
        List<Item> items = itemDao.findAllNotDeleted(firstResult, maxResults);
        logger.debug("Retrieved items : {}", items);
        if (!items.isEmpty()) {
            return itemDTOConverter.toDTOList(items);
        } else return Collections.emptyList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemDTO> findByVendorCode(SimpleItemDTO itemDTO) {
        logger.info("Retrieving Item by vendor code");
        logger.debug("Retrieving Item by vendor code : {}", itemDTO.getVendorCode());
        Item item = itemDao.findByVendorCode(itemDTO.getVendorCode());
        return Optional.ofNullable(item != null ? itemDTOConverter.toDto(item) : null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemDTO> findById(SimpleItemDTO itemDTO) {
        logger.info("Retrieving Item by Id");
        logger.info("Retrieving Item by Id : {}", itemDTO.getId());
        Item item = itemDao.findOne(itemDTO.getId());
        return Optional.ofNullable(item != null ? itemDTOConverter.toDto(item) : null);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAllNotDeleted() {
        logger.info("Counting all Items");
        return itemDao.countAllNotDeleted();
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        logger.info("Updating Item");
        logger.debug("Updating Item : {}", itemDTO);
        Item item = itemDao.findOne(itemDTO.getId());
        if (itemDTO.getName() != null) {
            item.setName(itemDTO.getName());
        }
        if (itemDTO.getDescription() != null) {
            item.setDescription(itemDTO.getDescription());
        }
        if (itemDTO.getVendorCode() != null) {
            Item itemByVendorCode = itemDao.findByVendorCode(itemDTO.getVendorCode());
            if (itemByVendorCode == null) {
                item.setVendorCode(itemDTO.getVendorCode());
            }
        }
        if (itemDTO.getPrice() != null) {
            item.setPrice(itemDTO.getPrice());
        }
        itemDao.update(item);
        return itemDTOConverter.toDto(item);
    }

    @Override
    public List<String> updateDiscountInPriceRange(DiscountDetails discountDetails) {
        logger.info("Updating Discount for Items");
        Discount discount = discountDao.findOne(discountDetails.getDiscountId());
        logger.info("Updating Discount ({}) for Items with price : {} - {}", discount.getName(), discountDetails.getMinPrice(), discountDetails.getMaxPrice());
        List<Item> items = itemDao.findInPriceRange(discountDetails.getMinPrice(), discountDetails.getMaxPrice());
        List<String> updatedItemVendorCodes = new ArrayList<>();
        for (Item item : items) {
            Iterator<Discount> discountIterator = item.getDiscounts().iterator();
            boolean discountAlreadyActive = false;
            while (discountIterator.hasNext()) {
                Discount activeDiscount = discountIterator.next();
                if (discountDetails.getDiscountId().equals(activeDiscount.getId())) {
                    discountAlreadyActive = true;
                    break;
                }
            }
            if (!discountAlreadyActive) {
                item.getDiscounts().add(discount);
                updatedItemVendorCodes.add(item.getVendorCode());
            }
            itemDao.update(item);
        }
        return updatedItemVendorCodes;
    }

    @Override
    public void deleteById(SimpleItemDTO simpleItemDTO) {
        logger.info("Deleting Item by Id");
        logger.debug("Deleting Item by Id : {}", simpleItemDTO.getId());
        itemDao.deleteById(simpleItemDTO.getId());
    }
}
