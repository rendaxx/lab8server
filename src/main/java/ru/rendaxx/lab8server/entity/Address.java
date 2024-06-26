package ru.rendaxx.lab8server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.rendaxx.lab8server.dto.AddressDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
@Transactional
public class Address implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @Size(min=1)
    private String street;

    @Column(nullable = false)
    private String zipcode;

    public Address(String street, String zipcode) {
        this.street = street;
        this.zipcode = zipcode;
    }

    public Address(AddressDto addressDto) {
        this(addressDto.getStreet(), addressDto.getZipCode());
    }
}
