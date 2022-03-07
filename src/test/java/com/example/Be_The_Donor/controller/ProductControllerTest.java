package com.example.Be_The_Donor.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class ProductControllerTest {

	@Mock
	private ProductService productService;

	private ObjectMapper mapper = new ObjectMapper();
	
	private MockMvc mockMvc;
	
	@InjectMocks
	ProductController controller;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

	@Test
	public void testUpdateProducts() throws Exception {
		Product product = new Product();
		product.setProductName("Dummy");
		product.setPrice(200.0d);
		product.setQuantity(5);
		
		when(productService.addQuantity(Mockito.any(Product.class))).thenReturn(product);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/updateProducts").content(mapper.writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		Product prdctResponse = mapper.readValue(result.getResponse().getContentAsString(), Product.class);

		assertThat(prdctResponse).isNotNull();
		assertEquals(prdctResponse.getProductName(), "Dummy");
		assertEquals(prdctResponse.getQuantity(), 5);

	}
	
	@Test
	public void testGetProducts() throws Exception {
		List<Product> listProduct = new ArrayList<Product>();
		Product product = new Product();
		product.setProductName("Dummy");
		product.setPrice(200.0d);
		listProduct.add(product);
		
		when(productService.getProducts()).thenReturn(listProduct);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getProducts")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		List<Product> prdctResponse = mapper.readValue(result.getResponse().getContentAsString(), List.class);

		assertThat(prdctResponse).isNotNull();

	}

}
