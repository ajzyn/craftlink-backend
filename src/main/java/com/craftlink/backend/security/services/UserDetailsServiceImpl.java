package com.craftlink.backend.security.services;

import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import com.craftlink.backend.security.models.UserPrincipal;
import com.craftlink.backend.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailWithSpecializations(email)
            .map(UserPrincipal::new)
            .orElseThrow(() -> new SecurityException(ExceptionCode.AUTHENTICATION_FAILED,
                "Invalid credentials for email: " + email));
    }
}
