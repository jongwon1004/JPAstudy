package jpastudy.hellojpa.domain6;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("BBB")
public class Book extends Item6 {

    private String author;
    private String isbn;
}
