package jpastudy.hellojpa.domain8;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member7 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    // Period
    @Embedded
    private Period workPeriod;

    // Address
    @Embedded
    private Address homeAddress;

}
