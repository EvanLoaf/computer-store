package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    List<UserDTO> findAll();

    SimpleUserDTO findByEmail(SimpleUserDTO userDTO);

    UserDTO update(UserDTO userDTO);
}
