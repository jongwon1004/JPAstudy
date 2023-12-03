package jpastudy.hellojpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    /*
        Hibernate:
            create table User (
                age integer,
                createdDate datetime(6),
                id bigint not null,
                lastModifiedDate datetime(6),
                name varchar(255),
                description tinytext,
                roleType enum ('USER','ADMIN'),
                primary key (id)
            ) engine=InnoDB
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 전략이 IDENTITY 일때는 em.persist 시점에 INSERT 쿼리가 날라감
    private Long id;

    @Column(name = "name", insertable = true, updatable = true, columnDefinition = "VARCHAR(20) DEFAULT 'EMPTY'")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    // 얘는 default 가 ORDINAL인데 , 그냥 STRING쓰는게 좋다 요구사항이 추가되서 GEUST라는게 추가되었을떄
    // GUEST, USER, ADMIM 이렇게 되어버리면 전에는 USER 가 0 이였는데, GUEST 가 0이 되어버리기떄문에 장애요인이 될 수 있음
    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
    // 자바8부터는 LocalDateTime이랑 LocalDate 가 추가되었기 떄문에 그냥 밑에처럼만 적어줘도 된다

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Lob // String에 @Lob를 쓰면 tinytext 로 나가는데 text로 하고싶으면
    @Column(columnDefinition = "TEXT") // 이렇게 적어주면 된다
    private String description;

    @Transient // 요건 DB 컬럼이랑 매핑하고싶지 않을떄, 메모리에서만 테스트용으로 사용하거나 할때 사용
    private int temp;

}
