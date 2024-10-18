package toysns.toysns.domain.member;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
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

}
