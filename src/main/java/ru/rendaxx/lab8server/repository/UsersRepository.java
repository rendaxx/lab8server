package ru.rendaxx.lab8server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rendaxx.lab8server.entity.Users;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
}