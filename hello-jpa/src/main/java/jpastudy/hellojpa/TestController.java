package jpastudy.hellojpa;

import jakarta.persistence.EntityManager;
import jpastudy.hellojpa.domain2.Member2;
import jpastudy.hellojpa.domain2.Team2;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final EntityManager em;

    @PostMapping("/test1")
    @Transactional(rollbackFor = Exception.class)
    public String test1() {

        Team2 team2 = new Team2();
        team2.setName("teamA");
        em.persist(team2);

        Member2 member2 = new Member2();
        member2.setName("memberA");
        member2.setTeam(team2);
        em.persist(member2);

        em.flush();
        em.clear();

        Member2 findMember = em.find(Member2.class, 1L);
        List<Member2> findMembers = findMember.getTeam().getMembers();

        for (Member2 member : findMembers) {
            System.out.println("member.getName() = " + member.getName());
        }

        return "";
    }

    @PostMapping("/test2")
    @Transactional(rollbackFor = Exception.class)
    public String test2() {

        Member2 member2 = new Member2();
        member2.setName("memberB");
        em.persist(member2);


        Team2 findTeam = em.find(Team2.class, 1L);
        findTeam.getMembers().add(member2);
        for (Member2 member : findTeam.getMembers()) {

            System.out.println("member = " + member.getName());
        }

        /*
            위의 코드를보면 그냥 하는짓이 거의 Team 이 연관관계의 주인처럼 하고있음
            그럼 어떻게 고쳐줘야 하나 ? test3을 보자
         */
        return "";
    }

    @PostMapping("/test3")
    @Transactional(rollbackFor = Exception.class)
    public String test4() {

        Team2 team2 = new Team2();
        team2.setName("teamE");
        em.persist(team2);

        Member2 member2 = new Member2();
        member2.setName("memberE");
        member2.setTeam(team2); /**/
        em.persist(member2);

//        team2.getMembers().add(member2); /**/
        // 양방향 연관관계에서는 객체지향적으로 양쪽에 값을 세팅해주는게 맞음
        // 순수 객체상태를 고려해서 항상 양쪽에 값을 설정하자



        em.flush();
        em.clear();

        Team2 findTeam = em.find(Team2.class, team2.getId());  // 여기서 select 쿼리 한방 나가고
        // findTeam 안에 members 데이터들을 끌고와서 실제 사용하는 시점에 select 쿼리 한방을 더 날림
        List<Member2> members = findTeam.getMembers();
        for (Member2 member : members) {
            System.out.println("member = " + member.getName());
        }


        return "";
    }
}
