package toysns.toysns.domain.member;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class Address {
    private String street;
    private String city;
    private String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = street == null ? "" : street;
        this.city = city == null ? "" : city;
        this.zipCode = zipCode == null ? "" : zipCode;
    }

    protected Address() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode);
    }
    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode);
    }
}
