package ru.rendaxx.lab8server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rendaxx.lab8server.dto.OrganizationDto;
import ru.rendaxx.lab8server.service.CollectionServiceImpl;

@Controller
public class UpdateController {
    private CollectionServiceImpl collectionService;

    public UpdateController(CollectionServiceImpl collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/updateCommand")
    public ResponseEntity updateOrganization(@RequestBody OrganizationDto organizationDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        collectionService.updateElement(organizationDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
