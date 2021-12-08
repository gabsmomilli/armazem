package br.gov.sp.fatec.frases.service.serviceImpl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.frases.model.Category;
import br.gov.sp.fatec.frases.model.Product;
import br.gov.sp.fatec.frases.repository.ProductRepository;
import br.gov.sp.fatec.frases.service.ProductService;
import br.gov.sp.fatec.frases.repository.CategoryRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired 
    ProductRepository prodRepo;

    @Autowired 
    CategoryRepository catRepo;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Product newProduct(String name, Integer peso, String site, String categories){

        Category cat = catRepo.findByName(categories);
        if(cat == null){
            cat = new Category();
            cat.setName("teste2");
            catRepo.save(cat);
        }
        

        Product product = new Product();
		product.setName("teste2");
        product.setPeso(14);
        product.setSite("www.armazem_da_gabi.com/produto-12345");
        product.setCategories(new HashSet<Category>());
        product.getCategories().add(cat);
		prodRepo.save(product);

        return product;

    }
    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public List<Product> findAllProducts(){
        return prodRepo.findAll();
    }

}
