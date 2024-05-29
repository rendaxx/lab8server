package ru.rendaxx.lab8server.dto;

import lombok.Data;
import ru.rendaxx.lab8server.entity.Address;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {
    private String street;
    private String zipCode;

    public AddressDto(Address address) {
        this.street = address.getStreet();
        this.zipCode = address.getZipcode();
    }
}
