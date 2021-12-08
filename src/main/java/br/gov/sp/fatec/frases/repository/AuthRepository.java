package br.gov.sp.fatec.frases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import br.gov.sp.fatec.frases.model.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long>{
    public List<Auth> findByUsersName(String name);

    public Auth findByName(String name);
}
