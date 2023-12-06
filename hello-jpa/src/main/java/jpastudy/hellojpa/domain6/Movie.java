package jpastudy.hellojpa.domain6;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("MMM")
public class Movie extends Item6 {

    private String director;
    private String actor;
}
