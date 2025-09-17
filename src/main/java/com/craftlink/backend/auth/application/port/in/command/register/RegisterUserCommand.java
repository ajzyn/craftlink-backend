package com.craftlink.backend.auth.application.port.in.command.register;

public record RegisterUserCommand(String email, String password, String username) {

}
