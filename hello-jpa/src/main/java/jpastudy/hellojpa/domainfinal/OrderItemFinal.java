package jpastudy.hellojpa.domainfinal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemFinal {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemFinal item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderFinal order;

    private int orderPrice;

    private int count;


}
