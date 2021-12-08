package br.gov.sp.fatec.frases;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.frases.model.Product;
import br.gov.sp.fatec.frases.repository.ProductRepository;
import br.gov.sp.fatec.frases.model.Category;
import br.gov.sp.fatec.frases.repository.CategoryRepository;

@SpringBootTest
@Transactional
@Rollback
class ProductTest {

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private CategoryRepository catRepo;

	@Test
	void findByNameTest() {
		Product produto = new Product();
		produto.setName("teste");
		prodRepo.save(produto);

		assertNotNull(prodRepo.findByName("teste"));
	}

	@Test
	void findByPesoOrSiteTest() {
		Product produto = new Product();
		produto.setName("teste");
        produto.setPeso(14);
        produto.setSite("www.armazem_da_gabi.com/produto-12345");
		prodRepo.save(produto);

		assertFalse(prodRepo.findByPesoOrSite(14, "test").isEmpty());
	}

	@Test
	void findAllByNameTest() {
		Category category = new Category();
		category.setName("Teste");
		catRepo.save(category);

		Product product = new Product();
		product.setName("maça");
		product.setPeso(12);
		product.setSite("www.mercado/maça");
		product.setCategories(new HashSet<Category>());
		product.getCategories().add(category);
		prodRepo.save(product);

		assertFalse(catRepo.findAllByName("Teste").isEmpty());
	}

}
