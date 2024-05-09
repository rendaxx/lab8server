package ru.rendaxx.lab8server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rendaxx.lab8server.entity.Organization;
import ru.rendaxx.lab8server.entity.Users;
import ru.rendaxx.lab8server.repository.AddressRepository;
import ru.rendaxx.lab8server.repository.CoordinatesRepository;
import ru.rendaxx.lab8server.repository.OrganizationRepository;

@Service
public class DatabaseService {
    private final UserService userService;
    private final OrganizationRepository organizationRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public DatabaseService(UserService userService, OrganizationRepository organizationRepository, CoordinatesRepository coordinatesRepository, AddressRepository addressRepository) {
        this.userService = userService;
        this.organizationRepository = organizationRepository;
        this.coordinatesRepository = coordinatesRepository;
        this.addressRepository = addressRepository;
    }

    public Organization insertOrganization(Organization organization, Users user) {
        var coordinates = coordinatesRepository.save(organization.getCoordinates());
        var address = addressRepository.save(organization.getAddress());
        var userFromDB = userService.findByLogin(user.getLogin()).get();
        organization.setCoordinates(coordinates);
        organization.setAddress(address);
        organization.setCreator(userFromDB);
        return organizationRepository.save(organization);
    }

    public void updateOrganization(Organization organization) {
        if (organizationRepository.existsById(organization.getId())) { // TODO add Users.Role check
            organizationRepository.save(organization);
        }
    }

    public void deleteOrganization(Organization organization) {
        if (organizationRepository.existsById(organization.getId())) { // TODO add Users.Role check
            organizationRepository.delete(organization);
        }
    }

}
