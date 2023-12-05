package jpastudy.hellojpa.domain3;

import jakarta.persistence.*;
import jpastudy.hellojpa.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "ORDERS")
public class Order3 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member3 member3;

    @OneToMany(mappedBy = "order3")
    private List<OrderItem3> orderItems = new ArrayList<>();

    private LocalDate orderDate;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

}
