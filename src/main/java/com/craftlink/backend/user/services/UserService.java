package com.craftlink.backend.user.services;

import com.craftlink.backend.auth.dtos.RegisterRequestDto;
import com.craftlink.backend.client.entities.ClientEntity;
import com.craftlink.backend.config.exceptions.custom.BusinessException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import com.craftlink.backend.specialist.entities.SpecialistEntity;
import com.craftlink.backend.user.entities.UserEntity;
import com.craftlink.backend.user.models.UserType;
import com.craftlink.backend.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Transactional
  public void registerClient(RegisterRequestDto registerRequestDto) {
    if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
      throw new BusinessException(ExceptionCode.USER_ALREADY_EXISTS);
    }

    var hashedPassword = passwordEncoder.encode(registerRequestDto.getPassword());

    var client = new ClientEntity();

    var user = UserEntity.builder()
        .email(registerRequestDto.getEmail())
        .password(hashedPassword)
        .userType(UserType.CLIENT)
        .build();

    user.setClient(client);

    try {
      userRepository.save(user);
    } catch (DataAccessException ex) {
      throw new BusinessException(
          ExceptionCode.SERVICE_UNAVAILABLE,
          ex
      );
    }
  }

  @Transactional
  public void registerSpecialist(RegisterRequestDto registerRequestDto) {
    if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
      throw new BusinessException(ExceptionCode.USER_ALREADY_EXISTS);
    }

    var hashedPassword = passwordEncoder.encode(registerRequestDto.getPassword());

    var specialist = new SpecialistEntity();

    var user = UserEntity.builder()
        .email(registerRequestDto.getEmail())
        .password(hashedPassword)
        .userType(UserType.SPECIALIST)
        .build();

    user.setSpecialist(specialist);

    try {
      userRepository.save(user);
    } catch (DataAccessException ex) {
      throw new BusinessException(
          ExceptionCode.SERVICE_UNAVAILABLE,
          ex
      );
    }
  }

  public UserEntity getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new BusinessException(ExceptionCode.USER_NOT_FOUND));
  }

  public Optional<UserEntity> getUserById(UUID id) {
    return userRepository.findById(id);
  }
}
