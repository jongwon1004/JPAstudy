package jpastudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManagerFactory;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final EntityManagerFactory em;




}
