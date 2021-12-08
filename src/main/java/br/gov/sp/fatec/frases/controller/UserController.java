package br.gov.sp.fatec.frases.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.gov.sp.fatec.frases.service.SecurityService;
import java.util.List;
import br.gov.sp.fatec.frases.model.Usuario;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin
@RequestMapping(value = "/usuario")
public class UserController {

    @Autowired
    private SecurityService securityService;

    @GetMapping
    @JsonView(View.UserSimplified.class)
    public List<Usuario> findAllUsers() {
        return securityService.findAllUsers();
    }

    @PostMapping
    @JsonView(View.UserCompleted.class)
    public Usuario newUser(@RequestBody Usuario user) {
        return securityService.newUser(user.getName(), user.getEmail(), user.getPassword(), "ROLE_USER");
    }
}