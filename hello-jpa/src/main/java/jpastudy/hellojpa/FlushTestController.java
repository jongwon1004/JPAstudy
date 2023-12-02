package jpastudy.hellojpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FlushTestController {

    private final EntityManager em;


    @PostMapping("/flushTest")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String flushTest() {

        Member member = new Member();
        member.setId(100L);
        member.setName("flush");

        em.persist(member);
        em.flush(); // flush 를 직접 호출하면 영속성 컨텍스트 안에 쓰기지연 SQL저장소에 있는 쿼리가 DB에 날라감

        /*
            플러시는
            1. 영속성 컨텍스트를 비우지 않음
            2. 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
            3. 트랜젝션이라는 작업단위가 중요 -> 커밋 직전에만 동기화 하면됨
         */

        return "";
    }

    @PostMapping("/detachTest")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String detachTest() {

        Member findMember = em.find(Member.class, 100L);
        findMember.setName("detach");

        em.detach(findMember);
        /*
            이렇게 실행하면 em.find할때 SELECT 쿼리만 나간다
            왜 ? 멤버값을 변경하긴 했지만 em.detach 로 영속석 컨텍스트에서 떼어내서 준영속 상태가 되었기 떄문에
            JPA 가 관리할 수 있는 엔티티가 아니기 떄문

            이처럼 영속성 상태였다가 영속성 컨텍스트에서 빠지는것을 준영속 상태라고 함
            특정 엔티티만 준영속 상태로 만드려면 em.detach() 를
            em 의 모든 엔티티를 준영속 상태로 만드려면 em.clear() 를 쓰면된다
         */

        // em.clear() 로 모든 엔티티를 준영속 상태로 만들고 밑에서 em.find() 를 하면 select 쿼리가 한번 더 나간다
        // 왜 ? em.clear() 를 했으니까 영속성 컨텍스트는 아무것도 없을거고, 당연히 1차캐시에도 값이 없으니 DB에 SELECT 쿼리를 날려서
        // 영속성 컨텍스트에 올림과 동시에 1차캐시에 저장됨
        Member findMember2 = em.find(Member.class, 100L);

        return "";
    }


}
