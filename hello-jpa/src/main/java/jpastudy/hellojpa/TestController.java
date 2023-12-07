package jpastudy.hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnitUtil;
import jpastudy.hellojpa.domain2.Member2;
import jpastudy.hellojpa.domain2.Team2;
import jpastudy.hellojpa.domain4.Member4;
import jpastudy.hellojpa.domain5.Member5;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
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

    @PostMapping("/test4")
    @Transactional
    public String test5() {

        Member4 member = new Member4();
        member.setName("jongwon");

        em.persist(member);

        em.flush();
        em.clear();

//        Member4 findMember = em.find(Member4.class, 1L);
        Member4 findMember = em.find(Member4.class, member.getId());
        System.out.println("findMember.getClass() = " + findMember.getClass());
        System.out.println("findMember.getId() = " + findMember.getId()); // getReference 할때 파라미터로 넘겨서 이미 알고있음 -> 쿼리 안나감
        System.out.println("findMember.getName() = " + findMember.getName()); // 새롭게 값을 가져옴 -> 쿼리 나감

        em.detach(findMember);
        em.clear();

//        Hibernate.initialize(findMember); // 강제 초기화

        boolean loaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(findMember);
        System.out.println("loaded = " + loaded);
        return "";
    }
}
