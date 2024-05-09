package ru.rendaxx.lab8server.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.rendaxx.lab8server.entity.*;

@SpringBootTest
@Rollback
@Transactional
class DatabaseServiceTest {
    private final DatabaseService databaseService;

    @Autowired
    public DatabaseServiceTest(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Test
    void insertOrganization() {
        var organization = new Organization("amongus", new Coordinates(1., 1.),
                null, 123L, "amohus", 123L, OrganizationType.PUBLIC,
                new Address("123", "123"));
        databaseService.insertOrganization(organization, new Users("login", "pass",
                Role.USER));
    }

    @Test
    void updateOrganization() {

    }
}