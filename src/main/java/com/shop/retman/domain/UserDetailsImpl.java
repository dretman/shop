package com.shop.retman.domain;

import com.shop.retman.dao.entity.Role;
import com.shop.retman.dao.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    private static final String ROLE_PREFIX = "ROLE_";

    private long id;
    private String username;
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(User user) {
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
        this.setAccountNonExpired(user.isAccountNonExpired());
        this.setAccountNonLocked(user.isAccountNonLocked());
        this.setCredentialsNonExpired(user.isCredentialsNonExpired());
        this.setEnabled(user.isEnabled());
        this.setAuthorities(user.getRoles());
    }

    public long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAuthorities(Set<Role> roleSet) {
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorityCollection = new ArrayList<>();
        for (Role role : roleSet) {
        simpleGrantedAuthorityCollection.add(new SimpleGrantedAuthority(ROLE_PREFIX.concat(role.getName())));
        }

        this.authorities = simpleGrantedAuthorityCollection;
    }
}
