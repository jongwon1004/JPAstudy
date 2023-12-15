package jpastudy.hellojpa.domainfinal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class AddressFinal {

    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 7)
    private String zipcode;

    // 값타입이라 이런 유요한 메서드를 만들어서 쓰거나 할 수 있음
    public String fullAddress() {
        return getCity() + " " + getStreet() + " " + getZipcode();
    }

    private void setCity(String city) {
        this.city = city;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressFinal that = (AddressFinal) o;
        return Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getZipcode(), that.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
