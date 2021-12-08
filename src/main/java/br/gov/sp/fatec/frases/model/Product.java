package br.gov.sp.fatec.frases.model;
import javax.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.frases.controller.View;

@Entity
@Table(name = "pro_produto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    @JsonView(View.ProductCompleted.class)
    private Long id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pca_produto_categoria",
        joinColumns = { @JoinColumn(name = "pro_id")},
        inverseJoinColumns = { @JoinColumn(name = "cat_id") })
    @JsonView(View.ProductSimplified.class)
    private Set<Category> categories;

    @Column(name = "pro_nome")
    @JsonView(View.ProductSimplified.class)
    private String name;

    @Column(name = "pro_peso")
    @JsonView(View.ProductCompleted.class)
    private Integer peso;

    @Column(name = "pro_site")
    @JsonView(View.ProductSimplified.class)
    private String site;

     @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Movement> movements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Category> getCategories() {
        return categories;
    }


    public Set<Movement> getMovements() {
        return movements;
    }

    public void setMovements(Set<Movement> movements) {
        this.movements = movements;
    }
    
}
