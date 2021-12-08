package br.gov.sp.fatec.frases.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.gov.sp.fatec.frases.service.ProductService;
import br.gov.sp.fatec.frases.model.Product;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @JsonView(View.ProductSimplified.class)
    public List<Product> findAllUsers() {
        return productService.findAllProducts();
    }

    @PostMapping
    @JsonView(View.ProductCompleted.class)
    public Product newProduct(@RequestBody Product product) {
        Product product1 = productService.newProduct(product.getName(), product.getPeso(), product.getSite(),
        "ROLE_CATEGORIES");
        return product1;
        //return productService.newProduct(product.getName(), product.getPeso(),
        // product.getSite(), "ROLE_CATEGORIES");
    }
}