package jpastudy.hellojpa;

import jakarta.persistence.EntityManager;
import jpastudy.hellojpa.domain6.Item6;
import jpastudy.hellojpa.domain6.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExtendController {

    private final EntityManager em;


    @PostMapping("/extend1")
    @Transactional
    public String extend1() {

        Movie movie = new Movie();
        movie.setActor("actor");
        movie.setDirector("director");
        movie.setName("실미도");
        movie.setPrice(2000);

        em.persist(movie);

        em.flush();
        em.clear();

        Movie findMovie = em.find(Movie.class, movie.getId());
        System.out.println("findMovie.getActor() = " + findMovie.getActor());
        return "";
    }


}
