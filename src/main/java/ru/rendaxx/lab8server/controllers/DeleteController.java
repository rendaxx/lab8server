package ru.rendaxx.lab8server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rendaxx.lab8server.dto.OrganizationDto;
import ru.rendaxx.lab8server.entity.Organization;
import ru.rendaxx.lab8server.service.CollectionServiceImpl;

@Controller
public class DeleteController {
    private CollectionServiceImpl collectionService;

    public DeleteController(CollectionServiceImpl collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/delete")
    public ResponseEntity deleteOrganization(@RequestBody OrganizationDto organizationDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        collectionService.deleteElement(organizationDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
