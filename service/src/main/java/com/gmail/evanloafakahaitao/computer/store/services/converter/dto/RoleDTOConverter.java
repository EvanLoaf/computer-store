package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Permission;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.PermissionDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;

public class RoleDTOConverter implements DTOConverter<RoleDTO, Role> {

    private final DTOConverter<PermissionDTO, Permission> permissionDTOConverter = new PermissionDTOConverter();

    @Override
    public RoleDTO toDto(Role entity) {
        RoleDTO role = new RoleDTO();
        role.setId(entity.getId());
        role.setName(entity.getName());
        if (!entity.getPermissions().isEmpty()) {
            role.setPermissions(
                    permissionDTOConverter.toDTOSet(entity.getPermissions())
            );
        }
        return role;
    }
}
