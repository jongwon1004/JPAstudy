package jpastudy.hellojpa.domain8;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
