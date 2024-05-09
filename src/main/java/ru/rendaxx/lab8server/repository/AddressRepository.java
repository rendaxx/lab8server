package ru.rendaxx.lab8server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rendaxx.lab8server.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
