package jpastudy.hellojpa.domain2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Team2 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") // Team 에게 있어선 Member 는 Many //  mappedBy 는 필드명으로 매핑
    // 1:N 관계에서는 N이 연관관계의 주인이 되어야 한다 현재 Team2는 1 이기 때문에 Member2 의 team에 매핑되었다고 적어준게 mappedBy
    // 외래키가 있는곳이 무조건 N 임 -> 외래키가 있는 곳을 주인으로 정해라
    // mappedBy 는 읽기 전용임 여기다 값을 넣어도 Member2 의 team에 쿼리 날라가지 않는다
    List<Member2> members = new ArrayList<>(); // 널포 방지를위해 ArrayList로 초기화 해놓음 -> 관레임
}
