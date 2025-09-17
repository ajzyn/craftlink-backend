package com.craftlink.backend.auth.adapter.out.security.config;

import com.craftlink.backend.auth.adapter.out.security.model.UserPrincipal;
import com.craftlink.backend.auth.application.port.out.read.UserQueryRepository;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.infrastructure.exceptions.custom.SecurityException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserQueryRepository userQueryRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userQueryRepository.findByEmailWithServices(new Email(email))
        .map(UserPrincipal::new)
        .orElseThrow(() -> new SecurityException(ExceptionCode.AUTHENTICATION_FAILED,
            "Invalid credentials for email: " + email));
  }
}
