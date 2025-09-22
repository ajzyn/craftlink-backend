package com.craftlink.backend.auth.application.port.in.command.register;

public interface RegisterClientUserUseCase {

  void handle(RegisterUserCommand cmd);
}
