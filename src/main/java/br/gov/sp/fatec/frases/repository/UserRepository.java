package br.gov.sp.fatec.frases.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.frases.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByName(String name);

    public List<Usuario> findByNameContainsOrEmailContains(String name, String email);

    public List<Usuario> findByAuthsName(String name);
}