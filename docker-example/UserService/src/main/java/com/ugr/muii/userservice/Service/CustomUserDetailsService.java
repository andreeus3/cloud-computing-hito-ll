package com.ugr.muii.userservice.Service;

import com.ugr.muii.userservice.Dao.UserDao;
import com.ugr.muii.userservice.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws
            UsernameNotFoundException {

        User user = userDao.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .disabled(!user.getActive())
                .build();
    }
}
