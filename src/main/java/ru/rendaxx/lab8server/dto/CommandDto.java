package ru.rendaxx.lab8server.dto;

import lombok.Value;

@Value
public class CommandDto {
    String name;
    Object[] args;
}
