package br.gov.sp.fatec.frases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.frases.model.Category;
import br.gov.sp.fatec.frases.model.Product;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    public Category findByName(String name);

    public List<Product> findAllByName(String name);
    
}

