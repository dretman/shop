package com.shop.retman.utils;

import com.shop.retman.dao.entity.User;
import com.shop.retman.domain.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    public static UserDetails generateUserDetails(User user) {
        return new UserDetailsImpl(user);
    }
}
