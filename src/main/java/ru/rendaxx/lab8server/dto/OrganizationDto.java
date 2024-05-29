package ru.rendaxx.lab8server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.rendaxx.lab8server.entity.Organization;
import ru.rendaxx.lab8server.entity.OrganizationType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrganizationDto implements Comparable<OrganizationDto> {
    private Long id;
    private String name;
    private Double x;
    private Double y;
    private LocalDate localDate;
    private Long annualTurnover;
    private String fullName;
    private Long employeesCount;
    private OrganizationType organizationType;
    private String street;
    private String zipCode;
    private String creatorName;

    public OrganizationDto(Organization org) {
        this.id = org.getId();
        this.name = org.getName();
        this.x = org.getCoordinates().getX();
        this.y = org.getCoordinates().getY();
        this.localDate = org.getLocalDate();
        this.annualTurnover = org.getAnnualTurnover();
        this.fullName = org.getFullName();
        this.employeesCount = org.getEmployeesCount();
        this.organizationType = org.getOrganizationType();
        this.street = org.getAddress().getStreet();
        this.zipCode = org.getAddress().getZipcode();
        this.creatorName = org.getCreator().getLogin();
    }

    @Override
    public int compareTo(OrganizationDto o) {
        return Long.compare(this.annualTurnover, o.annualTurnover);
    }
}
