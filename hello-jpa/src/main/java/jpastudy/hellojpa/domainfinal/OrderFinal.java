package jpastudy.hellojpa.domainfinal;

import jakarta.persistence.*;
import jpastudy.hellojpa.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderFinal {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberFinal member;

    @OneToMany(mappedBy = "order")
    private List<OrderItemFinal> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private DeliveryFinal delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
