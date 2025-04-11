package org.Evgeniy.service;

import org.Evgeniy.dao.UserDao;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.Evgeniy.model.User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(
                user.getUsername(),
                user.getPasswordHash(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}