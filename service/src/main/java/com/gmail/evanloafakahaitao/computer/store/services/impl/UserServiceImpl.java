package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DiscountDao;
import com.gmail.evanloafakahaitao.computer.store.dao.RoleDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.PermissionEnum;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.exceptions.UserNotFoundException;
import com.gmail.evanloafakahaitao.computer.store.services.model.DiscountDetails;
import com.gmail.evanloafakahaitao.computer.store.services.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final DiscountDao discountDao;
    private final DTOConverter<UserDTO, User> userDTOConverter;
    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;
    private final EntityConverter<UserDTO, User> userEntityConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            RoleDao roleDao,
            DiscountDao discountDao,
            @Qualifier("userDTOConverter") DTOConverter<UserDTO, User> userDTOConverter,
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter,
            @Qualifier("userEntityConverter") EntityConverter<UserDTO, User> userEntityConverter,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.discountDao = discountDao;
        this.userDTOConverter = userDTOConverter;
        this.simpleUserDTOConverter = simpleUserDTOConverter;
        this.discountDTOConverter = discountDTOConverter;
        this.userEntityConverter = userEntityConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        logger.info("Saving User");
        logger.debug("Saving User : {}", userDTO);
        Role role = roleDao.findDefault();
        User user = userEntityConverter.toEntity(userDTO);
        Profile profile = new Profile();
        profile.setUser(user);
        user.setProfile(profile);
        user.setDisabled(false);
        user.setDeleted(false);
        user.setRole(role);
        user.setPassword(
                bCryptPasswordEncoder.encode(userDTO.getPassword())
        );
        userDao.create(user);
        return userDTOConverter.toDto(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        logger.info("Updating User");
        logger.debug("Updating User with Id : {}", userDTO.getId());
        for (GrantedAuthority currentAuthority : CurrentUserUtil.getCurrentAuthorities()) {
            if (Objects.requireNonNull(
                    PermissionEnum.getPermission(currentAuthority.getAuthority()))
                    .equals(PermissionEnum.USER_BASIC_PERMISSION)
                    &&
                    !userDTO.getId().equals(CurrentUserUtil.getCurrentId())
            ) {
                throw new IllegalStateException("User is only allowed to update himself");
            }
        }
        User user = userDao.findOne(userDTO.getId());
        if (userDTO.getDisabled() != null) {
            user.setDisabled(userDTO.getDisabled());
            userDao.update(user);
        } else {
            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }
            if (userDTO.getPassword() != null && !userDTO.getPassword().equals("")) {
                user.setPassword(
                        bCryptPasswordEncoder.encode(userDTO.getPassword())
                );
            }
            if (userDTO.getProfile() != null) {
                if (userDTO.getProfile().getAddress() != null) {
                    user.getProfile().setAddress(userDTO.getProfile().getAddress());
                }
                if (userDTO.getProfile().getPhoneNumber() != null) {
                    user.getProfile().setPhoneNumber(userDTO.getProfile().getPhoneNumber());
                }
            }
            if (userDTO.getRole() != null) {
                Role role = roleDao.findOne(userDTO.getRole().getId());
                user.setRole(role);
            }
            userDao.update(user);
        }
        return userDTOConverter.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(SimpleUserDTO userDTO) {
        logger.info("Retrieving User by Id");
        logger.debug("Retrieving User by Id : {}", userDTO.getId());
        User user = userDao.findOne(userDTO.getId());
        if (user != null) {
            return userDTOConverter.toDto(user);
        } else {
            throw new UserNotFoundException("User was not found with Id : " + userDTO.getId());
        }
    }

    @Override
    public void deleteById(SimpleUserDTO userDTO) {
        logger.info("Deleting User by Id");
        logger.debug("Deleting User by Id : {}", userDTO.getId());
        userDao.deleteById(userDTO.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAllNotDeleted() {
        logger.info("Counting Users");
        return userDao.countAllNotDeleted();
    }

    @Override
    public DiscountDTO updateDiscountAll(DiscountDetails discountDetails) {
        Discount discount = discountDao.findOne(discountDetails.getDiscountId());
        List<User> users = userDao.findAll();
        for (User user : users) {
            user.setDiscount(discount);
            userDao.update(user);
        }
        return discountDTOConverter.toDto(discount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAllNotDeleted(Integer firstResult, Integer maxResults) {
        logger.info("Retrieving Users");
        List<User> users = userDao.findAllNotDeleted(firstResult, maxResults);
        logger.debug("Retrieved Users : {}", users);
        if (!users.isEmpty()) {
            return userDTOConverter.toDTOList(users);
        } else return Collections.emptyList();
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUserDTO findByEmail(SimpleUserDTO userDTO) {
        logger.info("Retrieving User by Email");
        logger.debug("Retrieving User by Email : {}", userDTO.getEmail());
        User user = userDao.findByEmail(userDTO.getEmail());
        if (user != null) {
            return simpleUserDTOConverter.toDto(user);
        } else {
            throw new UserNotFoundException("User was not found with Email : " + userDTO.getEmail());
        }
    }
}
