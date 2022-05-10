package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.model.DiscountDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO userDTO);
    //TODO mby change if remove @Where
    List<UserDTO> findAllNotDeleted(Integer firstResult, Integer maxResults);

    Optional<SimpleUserDTO> findByEmail(SimpleUserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO findById(SimpleUserDTO simpleUserDTO);

    void deleteById(SimpleUserDTO simpleUserDTO);
    //TODO mby change if remove @Where
    Long countAllNotDeleted();

    DiscountDTO updateDiscountAll(DiscountDetails discountDetails);
}
