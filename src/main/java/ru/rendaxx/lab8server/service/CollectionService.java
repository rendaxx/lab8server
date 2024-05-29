package ru.rendaxx.lab8server.service;

import ru.rendaxx.lab8server.dto.OrganizationDto;
import ru.rendaxx.lab8server.entity.Organization;

import java.util.Set;

public interface CollectionService {
    Set<OrganizationDto> load();
}
