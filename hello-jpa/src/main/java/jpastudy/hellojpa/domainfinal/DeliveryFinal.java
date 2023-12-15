package jpastudy.hellojpa.domainfinal;

import jakarta.persistence.*;
import jpastudy.hellojpa.domain.Order;
import jpastudy.hellojpa.domain5.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeliveryFinal {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private AddressFinal address;
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private OrderFinal order;


}
