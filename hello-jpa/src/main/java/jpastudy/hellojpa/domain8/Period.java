package jpastudy.hellojpa.domain8;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
