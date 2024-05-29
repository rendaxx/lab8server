package ru.rendaxx.lab8server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rendaxx.lab8server.dto.OrganizationDto;
import ru.rendaxx.lab8server.dto.UserDto;
import ru.rendaxx.lab8server.entity.Organization;
import ru.rendaxx.lab8server.service.CollectionServiceImpl;

@Controller
public class AddController {
    private CollectionServiceImpl collectionService;

    public AddController(CollectionServiceImpl collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/add")
    public ResponseEntity addOrganization(@RequestBody OrganizationDto organizationDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        collectionService.addElement(new Organization(organizationDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
