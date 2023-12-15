package jpastudy.hellojpa.domainfinal;

import jakarta.persistence.*;
import jpastudy.hellojpa.domain5.DeliveryStatus;
import jpastudy.hellojpa.domain8.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberFinal {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<OrderFinal> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
