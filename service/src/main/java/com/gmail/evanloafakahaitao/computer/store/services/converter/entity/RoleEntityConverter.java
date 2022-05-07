package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Permission;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.PermissionDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;

public class RoleEntityConverter implements EntityConverter<RoleDTO, Role> {

    private final EntityConverter<PermissionDTO, Permission> permissionEntityConverter = new PermissionEntityConverter();

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        if (dto.getId() != null) {
            role.setId(dto.getId());
        }
        if (dto.getName() != null) {
            role.setName(dto.getName());
        }
        if (!dto.getPermissions().isEmpty()) {
            role.setPermissions(
                    permissionEntityConverter.toEntitySet(dto.getPermissions())
            );
        }
        return role;
    }
}
