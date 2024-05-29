package ru.rendaxx.lab8server.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ru.rendaxx.lab8server.dto.OrganizationDto;
import ru.rendaxx.lab8server.entity.Organization;
import ru.rendaxx.lab8server.util.UpdateNotifier;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Component
@Getter
public class CollectionServiceImpl implements CollectionService {

    private Set<Organization> organizationSet;
    private final DatabaseServiceImpl databaseService;
    private UpdateNotifier updateNotifier;

    @Autowired
    public CollectionServiceImpl(Set<Organization> organizationSet, DatabaseServiceImpl databaseService, UpdateNotifier updateNotifier) {
        this.organizationSet = organizationSet;
        this.databaseService = databaseService;
        this.updateNotifier = updateNotifier;
    }

    @PostConstruct
    private void init() {
        organizationSet = databaseService.loadAll();
    }

    private Set<OrganizationDto> toDto(Set<Organization> organizations) {
        return organizations.stream().map(OrganizationDto::new).collect(Collectors.toSet());
    }

    public Set<OrganizationDto> load() {
        return toDto(getOrganizationSet());
    }

    private void removeById(Long id) {
        var oldOrg = organizationSet.stream().filter(o -> o.getId().equals(id)).findFirst().orElseThrow();
        organizationSet.remove(oldOrg);
        updateNotifier.notifyAction();
    }

    public void addElement(Organization organization) {
        var newOrganization = databaseService.insertOrganization(organization);
        organizationSet.add(newOrganization);
        updateNotifier.notifyAction();
    }

    public void updateElement(OrganizationDto organization) {
        var user = databaseService.getUserByName(organization.getCreatorName());
        var org = new Organization(organization, user);

        var userFromContext = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (databaseService.sameUser(userFromContext, org.getCreator())) {
            databaseService.updateOrganization(org);
//        removeById(organization.getId());
            organizationSet.add(org);
            updateNotifier.notifyAction();
        }
    }

    public void deleteElement(OrganizationDto organization) {
        var user = databaseService.getUserByName(organization.getCreatorName());
        var org = new Organization(organization, user);

        var userFromContext = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (databaseService.sameUser(userFromContext, org.getCreator())) {
            databaseService.deleteOrganization(org);
            removeById(org.getId());
            updateNotifier.notifyAction();
        }
    }
}
