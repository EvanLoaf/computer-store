package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.RoleDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.services.RoleService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleDao roleDao;
    private final DTOConverter<RoleDTO, Role> roleDTOConverter;

    @Autowired
    public RoleServiceImpl(
            RoleDao roleDao,
            @Qualifier("roleDTOConverter") DTOConverter<RoleDTO, Role> roleDTOConverter
    ) {
        this.roleDao = roleDao;
        this.roleDTOConverter = roleDTOConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        logger.info("Retrieving all Roles");
        List<Role> roles = roleDao.findAll();
        if (!roles.isEmpty()) {
            return roleDTOConverter.toDTOList(roles);
        } else return Collections.emptyList();
    }
}
