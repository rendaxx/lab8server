package ru.rendaxx.lab8server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rendaxx.lab8server.entity.Coordinates;

public interface CoordinatesRepository extends CrudRepository<Coordinates, Long> {
}
