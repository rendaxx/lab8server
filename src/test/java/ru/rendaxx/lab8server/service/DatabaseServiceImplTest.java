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
class DatabaseServiceImplTest {
    private final DatabaseServiceImpl databaseService;

    @Autowired
    public DatabaseServiceImplTest(DatabaseServiceImpl databaseService) {
        this.databaseService = databaseService;
    }

    @Test
    void insertOrganization() {

    }

    @Test
    void updateOrganization() {

    }
}