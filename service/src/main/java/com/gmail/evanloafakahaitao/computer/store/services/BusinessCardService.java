package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.BusinessCardDTO;

public interface BusinessCardService {

    BusinessCardDTO save(BusinessCardDTO businessCard);

    void deleteById(BusinessCardDTO businessCard);
}
