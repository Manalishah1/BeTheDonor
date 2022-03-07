package com.example.Be_The_Donor.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.repository.ProductRepository;
// To start the application context and to detect the start up issues 
@SpringBootTest
public class ProductServiceTest {
// creating a mock object of the class
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	ProductServiceImpl service;

	@Test
	public void testGetProduct() throws Exception {
		List<Product> listproduct = new ArrayList<Product>();
		Product product = new Product();
		product.setProductName("Dummy");
		product.setPrice(200.0d);
		product.setQuantity(5);
		listproduct.add(product);
		
		when(productRepository.findAll()).thenReturn(listproduct);

		List<Product> productResp = service.getProducts();
		assertThat(productResp).isNotNull();
		assertThat(productResp).hasSize(1);

	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		
		Product product = new Product();
		product.setProductName("Dummy");
		product.setPrice(200.0d);
		product.setQuantity(5);
		
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

		Product productResp = service.addQuantity(product);

		assertThat(productResp).isNotNull();

	}

}
