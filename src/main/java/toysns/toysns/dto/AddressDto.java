package toysns.toysns.dto;

import lombok.Data;
import toysns.toysns.domain.member.Address;

@Data
public class AddressDto {
    private String street;
    private String city;
    private String zipCode;

    public AddressDto(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public AddressDto(Address address){
        street = address.getStreet();
        city = address.getCity();
        zipCode = address.getZipCode();
    }
}
