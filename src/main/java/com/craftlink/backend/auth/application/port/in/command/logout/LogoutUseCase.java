package com.craftlink.backend.auth.application.port.in.command.logout;

public interface LogoutUseCase {

  void handle(LogoutCommand cmd);
}
