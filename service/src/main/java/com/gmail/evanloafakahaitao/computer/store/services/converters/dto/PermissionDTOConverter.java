package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Permission;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.PermissionDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.PermissionEnum;
import org.springframework.stereotype.Component;

@Component("permissionDTOConverter")
public class PermissionDTOConverter implements DTOConverter<PermissionDTO, Permission> {

    @Override
    public PermissionDTO toDto(Permission entity) {
        PermissionDTO permission = new PermissionDTO();
        permission.setId(entity.getId());
        permission.setName(
                PermissionEnum.getPermission(entity.getName().toString())
        );
        return permission;
    }
}
