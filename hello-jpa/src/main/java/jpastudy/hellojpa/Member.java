package jpastudy.hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity  // 서버 기동시  JPA가 사용하는애구나 내가 관리해야겠다 라는 애노테이션
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member") // 테이블 이름이랑 매핑할 객체 이름이 같으면 생략해줘도 됨 (컨벤션임) 만약에 name="user" 면 user 라는 테이블에 쿼리나감
/*
    @Entity 사용시 주의사항
    1. 기본생성자 필수
    2. final 클래스 enum, interface, inner 클래스에 사용 불가
    3. 저장할 필드에 final 사용불가
 */
public class Member {

    @Id // JPA 한테 PK 가 뭔지는 알려줘야함
    private Long id;

    @Column(name = "name") // @Table 이랑 똑같이 만약에 컬럼이름이 userName 이면 name="userName" 으로 적어주면 된다
    private String name;

    @Column
    private int age;
}
