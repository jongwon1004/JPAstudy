package jpastudy.hellojpa.domain4;

import jakarta.persistence.*;
import jpastudy.hellojpa.domain2.Team2;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "team_id") // 1:N 관계에서는 N이 연관관계의 주인이 되어야 한다
    private Team2 team;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

}


