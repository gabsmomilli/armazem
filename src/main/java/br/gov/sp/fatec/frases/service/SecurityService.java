package br.gov.sp.fatec.frases.service;
import java.util.List;
import br.gov.sp.fatec.frases.model.Usuario;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {
    public Usuario newUser(String name, String email, String password, String auth);

    public List<Usuario> findAllUsers();
}
