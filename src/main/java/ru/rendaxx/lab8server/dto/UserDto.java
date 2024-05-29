package ru.rendaxx.lab8server.dto;

import lombok.Value;

@Value
public class UserDto {
    String username;
    String rawPassword;
}
