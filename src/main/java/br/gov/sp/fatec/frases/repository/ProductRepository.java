package br.gov.sp.fatec.frases.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.frases.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    public Product findByName(String name);

    public List<Product> findByPesoOrSite(Integer peso, String site);

    public List<Product> findByMovementsWay(String way);

}