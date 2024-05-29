package ru.rendaxx.lab8server.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.rendaxx.lab8server.entity.Organization;
import ru.rendaxx.lab8server.entity.Users;
import ru.rendaxx.lab8server.repository.AddressRepository;
import ru.rendaxx.lab8server.repository.CoordinatesRepository;
import ru.rendaxx.lab8server.repository.OrganizationRepository;
import ru.rendaxx.lab8server.repository.UsersRepository;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DatabaseServiceImpl {
    private final UserService userService;
    private final OrganizationRepository organizationRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public DatabaseServiceImpl(UserService userService, OrganizationRepository organizationRepository, CoordinatesRepository coordinatesRepository, AddressRepository addressRepository, UsersRepository usersRepository) {
        this.userService = userService;
        this.organizationRepository = organizationRepository;
        this.coordinatesRepository = coordinatesRepository;
        this.addressRepository = addressRepository;
        this.usersRepository = usersRepository;
    }

    @Transactional
    public Set<Organization> loadAll() {
        return StreamSupport.stream(organizationRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Transactional
    public Organization insertOrganization(Organization organization) {
        var coordinates = coordinatesRepository.save(organization.getCoordinates());
        var address = addressRepository.save(organization.getAddress());
        User userFromContext = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users userFromDB = getUserByName(userFromContext.getUsername());
        organization.setCoordinates(coordinates);
        organization.setAddress(address);
        organization.setCreator(userFromDB);
        return organizationRepository.save(organization);
    }

    public void updateOrganization(Organization organization) {
        if (organizationRepository.existsById(organization.getId())) { // TODO add Users.Role check
            coordinatesRepository.save(organization.getCoordinates());
            addressRepository.save(organization.getAddress());
            organizationRepository.save(organization);
        }
    }

    public void deleteOrganization(Organization organization) {
        var userFromContext = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (organizationRepository.existsById(organization.getId()) && sameUser(userFromContext, organization.getCreator())) { // TODO make more readable
            organizationRepository.delete(organization);
        }
    }

    public Boolean sameUser(User userFromContext, Users creator) {
        return creator.getLogin().equals(userFromContext.getUsername());
    }

    public Users getUserByName(String name) {
        return usersRepository.findByLogin(name).get(); // TODO: fix
    }
}
