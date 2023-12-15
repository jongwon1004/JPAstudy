package jpastudy.hellojpa.domain7;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 영속성 전이 + 고아객체 생명주기 관리
    // Parent 엔티티와 연결이 끊어진 Child 엔티티(고아객체) 는 자동으로 삭제됨 
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children = new ArrayList<>();
}
