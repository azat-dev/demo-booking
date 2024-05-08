package com.azat4dev.demobooking.users.common.presentation.security.entities;

import com.azat4dev.demobooking.users.users_commands.domain.entities.User;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.services.EncodedPassword;
import com.azat4dev.demobooking.users.common.domain.values.UserId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private final UserId id;

    private final EncodedPassword password;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(
        UserId id,
        EncodedPassword password,
        Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal from(User user) {
        return new UserPrincipal(
            user.id(),
            user.encodedPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    public UserId id() {
        return id;
    }

    public String getUsername() {
        return id.toString();
    }

    public String getPassword() {
        return password.value();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
