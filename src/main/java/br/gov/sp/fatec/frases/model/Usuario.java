package br.gov.sp.fatec.frases.model;

import javax.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.frases.controller.View;

@Entity
@Table(name = "usr_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    @JsonView(View.UserCompleted.class)
    private Long id;

    public void setAuths(Set<Auth> auths) {
        this.auths = auths;
    }

    public Set<Auth> getAuths() {
        return auths;
    }

    public Long getId() {
        return id;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "uau_usuario_autorizacao",
        joinColumns = { @JoinColumn(name = "usr_id")},
        inverseJoinColumns = { @JoinColumn(name = "aut_id") })
    @JsonView(View.UserSimplified.class)
    private Set<Auth> auths;

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "usr_nome")
    @JsonView(View.UserSimplified.class)
    private String name;
    
    @Column(name = "usr_email")
    @JsonView(View.UserSimplified.class)
    private String email;

    @Column(name = "usr_senha")
    @JsonView(View.UserCompleted.class)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
