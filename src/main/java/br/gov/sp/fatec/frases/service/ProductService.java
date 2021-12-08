package br.gov.sp.fatec.frases.service;
import java.util.List;
import br.gov.sp.fatec.frases.model.Product;

public interface ProductService {
    public Product newProduct(String name, Integer peso, String site, String categories);

    public List<Product> findAllProducts();
}
