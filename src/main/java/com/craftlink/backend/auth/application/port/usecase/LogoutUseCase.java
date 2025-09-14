package com.craftlink.backend.auth.application.port.usecase;

import com.craftlink.backend.auth.application.dto.LogoutCommand;

public interface LogoutUseCase {

  void handle(LogoutCommand cmd);
}
