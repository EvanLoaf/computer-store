package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Permission;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.PermissionDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("roleDTOConverter")
public class RoleDTOConverter implements DTOConverter<RoleDTO, Role> {

    private final DTOConverter<PermissionDTO, Permission> permissionDTOConverter;

    @Autowired
    public RoleDTOConverter(
            @Qualifier("permissionDTOConverter") DTOConverter<PermissionDTO, Permission> permissionDTOConverter
    ) {
        this.permissionDTOConverter = permissionDTOConverter;
    }

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
