package br.gov.sp.fatec.frases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.frases.model.Movement;
import java.util.*;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    public List<Movement> findByProductName(String name);
}
