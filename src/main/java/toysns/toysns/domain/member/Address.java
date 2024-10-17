package toysns.toysns.domain.member;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class Address {
    @Size(max = 20, message = "도로명은 20자 이하여야 합니다.")
    private String street;
    @Size(max = 20, message = "도시 이름은 20자 이하여야 합니다.")
    private String city;
    @Size(max = 10, message = "우편번호는 10자 이하여야 합니다.")
    private String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = street == null ? "" : street;
        this.city = city == null ? "" : city;
        this.zipCode = zipCode == null ? "" : zipCode;
    }

    protected Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
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
