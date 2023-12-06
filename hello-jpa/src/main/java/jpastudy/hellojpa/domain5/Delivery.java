package jpastudy.hellojpa.domain5;

import jakarta.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order5 order;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

}
