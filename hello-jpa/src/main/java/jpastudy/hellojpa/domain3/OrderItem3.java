package jpastudy.hellojpa.domain3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class OrderItem3 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order3 order3;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item3 item3;

    private int orderPrice;
    private int count;
}
