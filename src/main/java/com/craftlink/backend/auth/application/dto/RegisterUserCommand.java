package com.craftlink.backend.auth.application.dto;

public record RegisterUserCommand(String email, String password, String username) {

}
