package ru.rendaxx.lab8server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.rendaxx.lab8server.dto.OrganizationDto;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "organization")
@DynamicUpdate
@Transactional
public class Organization implements BaseEntity<Long>, Comparable<Organization> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    private LocalDate localDate;

    @Column(nullable = false)
    private Long annualTurnover;

    private String fullName;

    @Column(nullable = false)
    private Long employeesCount;

    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Users creator;

    public Organization(String name, Coordinates coordinates, LocalDate localDate, Long annualTurnover, String fullName, Long employeesCount, OrganizationType organizationType, Address address) {
        this.name = name;
        this.coordinates = coordinates;
        this.localDate = localDate;
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.employeesCount = employeesCount;
        this.organizationType = organizationType;
        this.address = address;
    }

    public Organization(OrganizationDto org) {
        this.id = org.getId();
        this.name = org.getName();
        this.coordinates = new Coordinates(org.getX(), org.getY());
        this.localDate = org.getLocalDate();
        this.annualTurnover = org.getAnnualTurnover();
        this.fullName = org.getFullName();
        this.employeesCount = org.getEmployeesCount();
        this.organizationType = org.getOrganizationType();
        this.address = new Address(org.getStreet(), org.getZipCode());
    }


    public Organization(OrganizationDto org, Users user) {
        this.id = org.getId();
        this.name = org.getName();
        this.coordinates = new Coordinates(org.getX(), org.getY());
        this.localDate = org.getLocalDate();
        this.annualTurnover = org.getAnnualTurnover();
        this.fullName = org.getFullName();
        this.employeesCount = org.getEmployeesCount();
        this.organizationType = org.getOrganizationType();
        this.address = new Address(org.getStreet(), org.getZipCode());
        this.creator = user;
    }


    @Override
    public int compareTo(Organization o) {
        return (int) (this.getAnnualTurnover() - o.getAnnualTurnover());
    }
}
