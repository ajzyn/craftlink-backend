package com.craftlink.backend.user.services;

import com.craftlink.backend.auth.dtos.RegisterRequestDto;
import com.craftlink.backend.client.entities.ClientEntity;
import com.craftlink.backend.config.exceptions.ExceptionCode;
import com.craftlink.backend.config.exceptions.custom.UserAlreadyExistException;
import com.craftlink.backend.config.exceptions.custom.UserPersistenceException;
import com.craftlink.backend.specialist.entities.SpecialistEntity;
import com.craftlink.backend.user.entities.UserEntity;
import com.craftlink.backend.user.models.UserType;
import com.craftlink.backend.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
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
    public void registerClient(RegisterRequestDto registerRequestDto){
        if(userRepository.existsByEmail(registerRequestDto.getEmail())){
            throw new UserAlreadyExistException();
        }

        var hashedPassword = passwordEncoder.encode(registerRequestDto.getPassword());

        var client = new ClientEntity();

        var user = UserEntity.builder()
            .email(registerRequestDto.getEmail())
            .password(hashedPassword)
            .client(client)
            .build();

        try{
            userRepository.save(user);
        }catch (DataAccessException ex){
            throw new UserPersistenceException(ExceptionCode.CLIENT_NOT_REGISTERED, ex);
        }
    }

    @Transactional
    public void registerSpecialist(RegisterRequestDto registerRequestDto){
        if(userRepository.existsByEmail(registerRequestDto.getEmail())){
            throw new UserAlreadyExistException();
        }

        var hashedPassword = passwordEncoder.encode(registerRequestDto.getPassword());

        var specialist = new SpecialistEntity();

        var user = UserEntity.builder()
            .email(registerRequestDto.getEmail())
            .password(hashedPassword)
            .specialist(specialist)
            .build();

        try{
            userRepository.save(user);
        }catch (DataAccessException ex){
            throw new UserPersistenceException(ExceptionCode.SPECIALIST_NOT_REGISTERED, ex);
        }
    }
}
