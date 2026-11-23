package com.ugr.muii.userservice.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

    UserDetails loadUserByUsername(String username);
}
