package jpastudy.hellojpa.domain4;

import jakarta.persistence.*;
import jpastudy.hellojpa.domain2.Team2;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

@Entity
@Setter
@Getter
public class Member4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String name;

    // fetch = FetchType.LAZY 를 해놓으면 em.find(Member4.class, PK) 이렇게 하면 Member 테이블만 조회하게 됨
    // 그러나서 getTeam() 을 하면 Team 필드는 프록시로 가져오는걸 알 수 있음. getTeam().getName() 같은 프록시의 메서드를 터치(사용)할때
    // 값이 초기화 된다(DB조회) // Target 에는 아직 값이 없는데, 터치하는순간 DB를 조회해서 실제 엔티티에 값을 뿌려주고 target을 이용해서
    // 실제 엔티티에서 값을 가져옴
    // 멤버와 팀이 항상 사용되거나 함께 사용될때가 많은경우는 FetchType.EAGER 로 해주면 된다 -> em.find로 조회할때 그냥 바로 JOIN해버림
    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩 LAZY 를 사용해서 프록시로 조회
    @JoinColumn(name = "team_id") // 1:N 관계에서는 N이 연관관계의 주인이 되어야 한다
    private Team2 team;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

}


