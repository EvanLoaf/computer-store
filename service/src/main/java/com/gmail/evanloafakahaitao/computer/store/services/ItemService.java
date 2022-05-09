package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.model.DiscountDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<String> save(MultipartFile multipartFile);

    ItemDTO save(ItemDTO itemDTO);
    //TODO or findallnotdeleted if i remove @Where
    List<ItemDTO> findAll(Integer firstResult, Integer maxResults);

    Optional<ItemDTO> findByVendorCode(SimpleItemDTO itemDTO);

    Optional<ItemDTO> findById(SimpleItemDTO itemDTO);
    //TODO or countallnotdeleted if i remove @Where
    Long countAll();

    ItemDTO update(ItemDTO itemDTO);

    List<String> updateDiscountInPriceRange(DiscountDetails discountDetails);

}
