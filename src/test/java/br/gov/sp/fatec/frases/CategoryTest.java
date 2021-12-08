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
class CategoryTest {

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private CategoryRepository catRepo;

	@Test
	void findByNameTest() {
		Category category = new Category();
		category.setName("teste");
		category.setDescription("description");
		category.setRecommendation("recommendation");
		catRepo.save(category);

		assertNotNull(catRepo.findByName("teste"));
	}


	@Test
	void findByProductNameTest() {
		Category category = new Category();
		category.setName("Teste");
		category.setDescription("description");
		category.setRecommendation("recommendation");
		catRepo.save(category);

		Product product = new Product();
		product.setName("maça");
		product.setPeso(12);
		product.setSite("www.mercado/maça");
		product.setCategories(new HashSet<Category>());
		product.getCategories().add(category);
		prodRepo.save(product);

		assertFalse(catRepo.findAllByName("maça").isEmpty());
	}

}
