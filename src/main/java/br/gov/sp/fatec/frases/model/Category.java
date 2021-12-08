package br.gov.sp.fatec.frases.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.frases.controller.View;

import java.util.*;
 
@Entity
@Table(name = "cat_categoria")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    @JsonView(View.ProductCompleted.class)
    private Long id;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    @JsonView(View.UserCompleted.class)
    private Set<Product> products;

    @Column(name = "cat_nome")
    @JsonView(View.ProductSimplified.class)
    private String name;

    @Column(name = "cat_descricao")
    @JsonView(View.UserCompleted.class)
    private String description;
    
    @Column(name = "cat_recomendacao")
    @JsonView(View.UserCompleted.class)
    private String recommendation;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    
}
