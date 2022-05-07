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
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final DiscountDao discountDao;
    private final DTOConverter<UserDTO, User> userDTOConverter;
    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;
    private final EntityConverter<UserDTO, User> userEntityConverter;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            RoleDao roleDao,
            DiscountDao discountDao,
            @Qualifier("userDTOConverter") DTOConverter<UserDTO, User> userDTOConverter,
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter,
            @Qualifier("userEntityConverter") EntityConverter<UserDTO, User> userEntityConverter
    ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.discountDao = discountDao;
        this.userDTOConverter = userDTOConverter;
        this.simpleUserDTOConverter = simpleUserDTOConverter;
        this.userEntityConverter = userEntityConverter;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userEntityConverter.toEntity(userDTO);
            Profile profile = new Profile();
            user.setProfile(profile);
            user.getProfile().setUser(user);
            //TODO default role
            Role role = roleDao.findByName("user");
            user.setRole(role);
            userDao.create(user);
            UserDTO savedUser = userDTOConverter.toDto(user);
            transaction.commit();
            return savedUser;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save user", e);
        }
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(userDTO.getEmail());
            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }
            if (userDTO.getPassword() != null) {
                user.setPassword(userDTO.getPassword());
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
                Role role = roleDao.findByName(userDTO.getRole().getName());
                user.setRole(role);
            }
            if (userDTO.getDiscount() != null && userDTO.getDiscount().getPercent() != null) {
                Discount discount = discountDao.findOne(userDTO.getDiscount().getId());
                user.setDiscount(discount);
            }
            userDao.update(user);
            UserDTO updatedUser = userDTOConverter.toDto(user);
            transaction.commit();
            return updatedUser;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update user", e);
        }
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        Session session = userDao.getCurrentSession();
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<User> users = userDao.findAll();
            List<UserDTO> usersDTO = userDTOConverter.toDTOList(users);
            transaction.commit();
            return usersDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve users", e);
        }
        return userDTOList;
    }

    @Override
    public SimpleUserDTO findByEmail(SimpleUserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(userDTO.getEmail());
            SimpleUserDTO foundUser = simpleUserDTOConverter.toDto(user);
            transaction.commit();
            return foundUser;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve user by email", e);
        }
        return null;
    }
}
