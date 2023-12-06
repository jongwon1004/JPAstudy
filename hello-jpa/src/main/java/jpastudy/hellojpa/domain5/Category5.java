package jpastudy.hellojpa.domain5;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Category5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category5 parent;

    @OneToOne(mappedBy = "parent")
    private List<Category5> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item5> items = new ArrayList<>();
}
