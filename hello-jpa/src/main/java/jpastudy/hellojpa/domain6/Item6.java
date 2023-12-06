package jpastudy.hellojpa.domain6;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // default 는 싱글테이블임
@DiscriminatorColumn // (name = "DIS_TYPE")
// 구현 클래스마다 테이블 전략 을 사용하면 InheritanceType = InheritanceType.TABLE_PER_CLASS 로 바꿔주고
// Item 클래스는 abstract 클래스로 바꿔줘야함
@Getter
@Setter
public class Item6 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
}
