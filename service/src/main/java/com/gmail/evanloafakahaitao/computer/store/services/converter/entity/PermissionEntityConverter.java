package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Permission;
import com.gmail.evanloafakahaitao.computer.store.dao.model.PermissionEnum;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.PermissionDTO;

public class PermissionEntityConverter implements EntityConverter<PermissionDTO, Permission> {

    @Override
    public Permission toEntity(PermissionDTO dto) {
        Permission permission = new Permission();
        if (dto.getId() != null) {
            permission.setId(dto.getId());
        }
        if (dto.getName() != null) {
            permission.setName(
                    PermissionEnum.getPermission(dto.getName().toString())
            );
        }
        return permission;
    }
}
