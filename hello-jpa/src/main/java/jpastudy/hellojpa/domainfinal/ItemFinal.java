package jpastudy.hellojpa.domainfinal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ItemFinal {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;


//    private List<CategoryFinal> categories = new ArrayList<>();
}
