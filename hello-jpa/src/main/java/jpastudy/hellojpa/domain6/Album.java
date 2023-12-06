package jpastudy.hellojpa.domain6;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("AAA") // default -> entity name   새로운 Album 을 추가했을때 DB의 Item값에 DTYPE 값에는 AAA가 들어갈거임
// @DiscriminatorValue 는 조인전략에서는 필요하면 쓰면되지만, 싱글테이블 전략에서는 무조건 필수적으로 사용해야 한다.
public class Album extends Item6 {

    private String artist;
}
