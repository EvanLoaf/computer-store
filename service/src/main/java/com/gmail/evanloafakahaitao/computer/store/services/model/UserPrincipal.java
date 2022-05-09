package com.gmail.evanloafakahaitao.computer.store.services.model;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Permission;
import com.gmail.evanloafakahaitao.computer.store.dao.model.PermissionEnum;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -5122651040437919305L;

    private Long id;
    private String name;
    private String username;
    private String password;
    private boolean isDeleted;
    private boolean isDisabled;
    private List<GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.isDisabled = user.getDisabled();
        this.isDeleted = user.getDeleted();
        String[] authorityList = user.getRole()
                .getPermissions()
                .stream()
                .map(Permission::getName)
                .map(PermissionEnum::toString)
                .toArray(String[]::new);
        this.authorities = AuthorityUtils.createAuthorityList(authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    @Override
    public boolean isEnabled() {
        return !isDisabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
