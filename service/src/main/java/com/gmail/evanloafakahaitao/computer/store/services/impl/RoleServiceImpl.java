package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.RoleDao;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.RoleDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.services.RoleService;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.dto.RoleDTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleDao roleDao = new RoleDaoImpl();
    private final DTOConverter<RoleDTO, Role> roleDTOConverter = new RoleDTOConverter();

    @Override
    public List<RoleDTO> findAll() {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Role> roles = roleDao.findAll();
            List<RoleDTO> rolesDTO = roleDTOConverter.toDTOList(roles);
            transaction.commit();
            return rolesDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Roles", e);
        }
        return Collections.emptyList();
    }
}
