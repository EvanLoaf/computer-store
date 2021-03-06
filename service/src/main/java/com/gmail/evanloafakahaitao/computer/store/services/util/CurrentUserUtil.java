package com.gmail.evanloafakahaitao.computer.store.services.util;

import com.gmail.evanloafakahaitao.computer.store.services.model.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CurrentUserUtil {

    private static final Logger logger = LogManager.getLogger(CurrentUserUtil.class);

    private CurrentUserUtil() {
    }

    private static UserPrincipal getCurrentUser() {
        logger.debug("Extracting and returning Current User from context");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    public static Collection<? extends GrantedAuthority> getCurrentAuthorities() {
        logger.debug("Extracting and returning Current User Authorities from context");
        return getCurrentUser().getAuthorities();
    }

    public static Long getCurrentId() {
        logger.debug("Extracting and returning Current User Id from context");
        return getCurrentUser().getId();
    }
}
