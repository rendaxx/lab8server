package ru.rendaxx.lab8server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rendaxx.lab8server.entity.Organization;


public interface OrganizationRepository extends CrudRepository<Organization, Long> {

}
