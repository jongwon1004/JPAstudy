package jpastudy.hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    private final EntityManager em;

    /*
        주의

        1. EntityManagerFactory 는 하나만 생성해서 애플리케이션 전체에서 공유
        2. EntityManager 는 쓰레드간의 공유X (사용하고 버려야함)
        3. JPA 의 모든 데이터 변경은 트랜젝션 안에서 실행 **
     */

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/registerMember")
    public String registerMember() {
        Member member = new Member();
        member.setId(2L);
        member.setName("changwoo");
        em.persist(member);
        return "";
    }

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/getMember")
    public String getMember() {
        Member findMember = em.find(Member.class, 1L);// java 컬렉션처럼 이해하면됨. 내 객체를 대신 저장해주는 애라고 생각하면 됨
        log.info("findMember={}, name={} ", findMember.getId(), findMember.getName()); // findMember=1, name=jongwon

        return "";
    }

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/removeMember")
    public String removeMember() {
        Member findMember = em.find(Member.class, 1L);// java 컬렉션처럼 이해하면됨. 내 객체를 대신 저장해주는 애라고 생각하면 됨
        em.remove(findMember);

        return "";
    }

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/modifyMember")
    public String modifyMember() {
        Member findMember = em.find(Member.class, 1L);
        findMember.setName("modifyJongwon");

        /*
         setName 으로 이름을 수정하고나서 em.persist 를 하지 않아도 자동으로 이름이 바뀐걸 알 수 있다.
         왜그럴까 ? 이게 어떻게 가능한거지 ?
         JPA를 통해서 Member를 가져오면 JPA가 Member를 관리하게됨,
         JPA 가 변경이 됐는지 안됐는지 트랜젝션 커밋하는 시점에 다 체크를함
         그래서 어 ? 얘가 뭔가 값이 바뀌었네 ? 하면 커밋하기전에 업데이트 쿼리를 날리고 커밋을 딱 날림
         */
        return "";
    }

    /*
        만약에 나이가 18세 이상인 회원을 조회하고 싶다거나, 모든 회원 정보들을 가져오려고 하면 em.find로는 한계가 있다
        그래서 JPQL 이 존재한다
     */

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/getMembers")
    public String getMembers() {

        List<Member> result = em.createQuery("select m from Member as m where m.id > 1", Member.class)
//                .setFirstResult(5)
//                .setMaxResults(8)  -> 페이징도 쉽게 가능하다 5번부터 8개 가져와
                .getResultList(); // 테이블을 대상으로 쿼리를 날리는게 아니고 객체를 대상으로 쿼리를 날림 -> 회원 다 가져와!

        /*
            JPA 를 사용하면 엔티티 객체를 중심으로 개발
            문제는 검색 쿼리
            검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
            모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
            애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL 이 필요
         */

        /*
            JPQL

            JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
            SQL 과 문법 유사, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN  지원
            JPQL은 엔티티 객체를 대상으로 쿼리
            SQL은 데이터베이스 테이블을 대상으로 쿼리
         */


        for (Member member : result) {
            System.out.println("member.getName() = " + member.getName());
        }
        return "";
    }

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/userTest")
    public String useTest() {
        User user = new User();
        user.setId(1L);
        user.setAge(20);
        user.setDescription("hello my name is jongwon");
        user.setRoleType(RoleType.ADMIN);
        user.setCreatedDate(LocalDateTime.now());
        em.persist(user);
        return "";
    }


    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/identityTest")
    public String identityTest() {
        Member member = new Member();
        member.setName("jongwon");
        member.setAge(20);

        System.out.println("===================");
        em.persist(member);
        System.out.println("member.getId() = " + member.getId());
        System.out.println("===================");

        /*
            로그를 확인해보면 알겠지만 ==== 사이에 insert 쿼리가 DB에 날라간다
            원래는 em.persist 할때 쿼리가 나가는게 아니고 commit 시점에 쿼리가 날라가는데 왜 persist만 했는데 쿼리가 날라가지 ?
            DB에 값을 넣기전까지 PK값을 모르니까  영속성 컨텍스트 안의 1차캐시에 값을 넣을 수 없기때문에
            persist 하는 시점에 DB에 쿼리를 날리고 동시에 1차캐시에 PK값이 들어가게한다
            em.persist 하고 바로 member.getId()로 PK값을 확인하면 값을 얻을 수 있는걸 확인할 수 있다.

            ===================
            Hibernate:
                 insert for
                    jpastudy.hellojpa.Member insert
                            into
                    member (age, name)
                    values
                            (?, ?)
             member.getId() = 3
             ===================
        */
        return "";

    }
}
