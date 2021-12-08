package br.gov.sp.fatec.frases;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.frases.model.*;
import br.gov.sp.fatec.frases.repository.*;
import br.gov.sp.fatec.frases.service.*;

@SpringBootTest
@Transactional
@Rollback
class FrasesApplicationTests {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AuthRepository authRepo;

	@Autowired
	private MovementRepository movRepo;

	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private SecurityService secService;

	@Test
	void findByNameTest() {
		Usuario user = new Usuario();
		user.setName("teste");
		user.setEmail("teste@teste.com");
		user.setPassword("12345");
		userRepo.save(user);

		assertNotNull(userRepo.findByName("teste"));
	}

	@Test
	void findByNameContainsOrEmailContainsTest() {
		Usuario user = new Usuario();
		user.setName("teste");
		user.setEmail("lala@lalal.com");
		user.setPassword("12345");
		userRepo.save(user);

		assertFalse(userRepo.findByNameContainsOrEmailContains("lala@lalal.com", "test").isEmpty());
	}

	@Test
	void findByAuthsNameTest() {
		Auth auth = new Auth();
		auth.setName("Teste");
		authRepo.save(auth);

		Usuario user = new Usuario();
		user.setName("teste");
		user.setEmail("lala@lalal.com");
		user.setPassword("12345");
		user.setAuths(new HashSet<Auth>());
		user.getAuths().add(auth);
		userRepo.save(user);

		assertFalse(userRepo.findByAuthsName("Teste").isEmpty());
	}

	@Test
	void findByUsersNameTest() {
		Auth auth = new Auth();
		auth.setName("Teste");
		authRepo.save(auth);

		Usuario user = new Usuario();
		user.setName("teste");
		user.setEmail("lala@lalal.com");
		user.setPassword("12345");
		user.setAuths(new HashSet<Auth>());
		user.getAuths().add(auth);
		userRepo.save(user);

		assertFalse(authRepo.findByUsersName("Teste").isEmpty());
	}

	@Test
	void findByProductNameTest(){
		Product product = new Product();
		product.setName("teste");
		product.setPeso(14);
		product.setSite("site");
		prodRepo.save(product);

		Movement movement = new Movement();
		movement.setDate(new Date());
		movement.setQuantity(5);
		movement.setWay("sul do estado");
		movement.setProduct(product);
		movRepo.save(movement);

		assertFalse(movRepo.findByProductName("teste").isEmpty());
	}

	@Test
	void newUserTest() {

		Usuario user = secService.newUser("Test", "teste@test.com", "123", "ROLE_TEST");

		assertNotNull(userRepo.findByName("Test"));
	}

}
