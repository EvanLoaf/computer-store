package com.gmail.evanloafakahaitao.computer.store.services.converters.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Permission;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.PermissionDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("roleEntityConverter")
public class RoleEntityConverter implements EntityConverter<RoleDTO, Role> {

    private final EntityConverter<PermissionDTO, Permission> permissionEntityConverter;

    @Autowired
    public RoleEntityConverter(
            @Qualifier("permissionEntityConverter") EntityConverter<PermissionDTO, Permission> permissionEntityConverter
    ) {
        this.permissionEntityConverter = permissionEntityConverter;
    }

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
