package ru.rendaxx.lab8server;

import lombok.Value;

@Value
public class UserDto {
    String username;
    String rawPassword;
}
