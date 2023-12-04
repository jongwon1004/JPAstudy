package jpastudy.hellojpa.domain2;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member2 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id") // 1:N 관계에서는 N이 연관관계의 주인이 되어야 한다
    private Team2 team;

    // 연관관계 편의메서드 Member 의 team을 세팅해줌과 동시에 Team에 Member을 추가
    public void setTeam(Team2 team) { // 관례문에 set을 쓰는데 자기가 알아볼 수 있게 changeTeam 같은걸로 이름 바꿔서 써도됨 
        this.team = team;
        team.getMembers().add(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
