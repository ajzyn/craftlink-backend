package com.craftlink.backend.auth.adapter.security;

import com.craftlink.backend.auth.adapter.config.models.UserPrincipal;
import com.craftlink.backend.auth.application.port.UserAuthRepository;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserAuthRepository userAuthRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userAuthRepository.findByEmailWithServices(new Email(email))
        .map(UserPrincipal::new)
        .orElseThrow(() -> new SecurityException(ExceptionCode.AUTHENTICATION_FAILED,
            "Invalid credentials for email: " + email));
  }
}
