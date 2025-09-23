package com.craftlink.backend.auth.application.command;

import com.craftlink.backend.auth.adapter.out.security.PasswordHasherAdapter;
import com.craftlink.backend.auth.application.port.in.command.register.RegisterUserCommand;
import com.craftlink.backend.auth.application.port.in.command.register.RegisterUserUseCase;
import com.craftlink.backend.auth.application.port.out.write.UserRepository;
import com.craftlink.backend.auth.domain.events.UserRegisteredEvent;
import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.Password;
import com.craftlink.backend.auth.domain.model.user.vo.Username;
import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.shared.application.port.out.DomainEventPublisher;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterUserHandler implements RegisterUserUseCase {

  private final UserRepository userRepository;
  private final PasswordHasherAdapter passwordHasherAdapter;
  private final DomainEventPublisher eventPublisher;

  @Override
  @Transactional
  public void handle(RegisterUserCommand cmd) {
    var email = new Email(cmd.email());

    if (userRepository.existByEmail(email)) {
      throw new BusinessException(ExceptionCode.USER_ALREADY_EXISTS);
    }

    var hashedPassword = passwordHasherAdapter.hash(cmd.password());

    var user = User.register(
        new Email(cmd.email()),
        new Username(cmd.username()),
        new Password(hashedPassword),
        cmd.userType(),
        Set.of()
    );

    userRepository.save(user);

    eventPublisher.publish(
        new UserRegisteredEvent(user.getId().value(), user.getUserType())
    );
  }
}
