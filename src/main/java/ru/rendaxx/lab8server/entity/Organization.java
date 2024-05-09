package ru.rendaxx.lab8server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "organization")
@DynamicUpdate
public class Organization implements BaseEntity<Long> {

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
}
