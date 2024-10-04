package toysns.toysns.domain.member;

import org.junit.jupiter.api.Test;
import toysns.toysns.dto.AddressDto;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void 적절한_주소_등록_확인() {
        Address address = new Address("123 Main St", "Seoul", "12345");
        assertEquals("123 Main St", address.getStreet());
        assertEquals("Seoul", address.getCity());
        assertEquals("12345", address.getZipCode());
    }

    @Test
    void 적절한_null값_처리_확인() {
        Address address = new Address(null, null, null);
        assertEquals("", address.getStreet());
        assertEquals("", address.getCity());
        assertEquals("", address.getZipCode());
    }

    @Test
    void 동등성_확인() {
        Address address1 = new Address("123 Main St", "Seoul", "12345");
        Address address2 = new Address("123 Main St", "Seoul", "12345");
        Address address3 = new Address("456 Oak Rd", "Busan", "67890");

        assertEquals(address1, address2);
        assertNotEquals(address1, address3);
        assertEquals(address1.hashCode(), address2.hashCode());
        assertNotEquals(address1.hashCode(), address3.hashCode());
    }

    @Test
    void 다른_타입_비교_검사() {
        Address address = new Address("123 Main St", "Seoul", "12345");
        assertNotEquals(address, "Not an Address object");
    }

    @Test
    void null_비교_검사() {
        Address address = new Address("123 Main St", "Seoul", "12345");
        assertNotEquals(null, address);
    }
}