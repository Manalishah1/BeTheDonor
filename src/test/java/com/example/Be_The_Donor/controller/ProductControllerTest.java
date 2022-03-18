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
// To start the application context and to detect the start up issues
@SpringBootTest
public class ProductControllerTest {
	// creating a mock object of the class ProductRepository
	@Mock
	private ProductService productService;

	private ObjectMapper mapper = new ObjectMapper();
	
	private MockMvc mockMvc;
	// calling junit - injecting the mock object in ProductController class
	@InjectMocks
	ProductController controller;
// This method to be executed before each invocation of testUpdateProducts
    @BeforeEach
    public void setup() {
//    	Receiving the controller objects as parameters, building MOCKMvc instance by registering the controller instance
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

//    Creating mock product with the name "Dummy", with price 200 and with quantity 5
	@Test
	public void testUpdateProducts() throws Exception {
		Product product = new Product();
		product.setProductName("Dummy");
		product.setPrice(200.0d);
		product.setQuantity(5);
//		Checking with dummy data, passing any instance of product, if exists, then returning the product
		when(productService.addQuantity(Mockito.any(Product.class))).thenReturn(product);
//      Creating request builder to make request
//		mapper converts JSON object to JSON String
//		Calling API
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/updateProducts").content(mapper.writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON);
//  API value is got
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// Sending dummy product to API
//		Getting the response as JSON String  and converting it to JSON
		Product prdctResponse = mapper.readValue(result.getResponse().getContentAsString(), Product.class);
// Checking with dummy data to see if it is not null, if it has a quantity
		assertThat(prdctResponse).isNotNull();
		assertEquals(prdctResponse.getProductName(), "Dummy");
		assertEquals(prdctResponse.getQuantity(), 5);

	}
	//    Creating mock product with the name "Dummy", with price 200 and with quantity 5
	@Test
	public void testGetProducts() throws Exception {
		List<Product> listProduct = new ArrayList<Product>();
		Product product = new Product();
		product.setProductName("Dummy");
		product.setPrice(200.0d);
		listProduct.add(product);

//		Checking with dummy data, if able to get the products, then returning the list of products
		when(productService.getProducts()).thenReturn(listProduct);
//      Creating request builder to make request
//		mapper converts JSON object to JSON String
//		Calling API
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getProducts")
				.contentType(MediaType.APPLICATION_JSON);
//  API value is got
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		Getting the response as JSON String  and converting it to JSON
		List<Product> prdctResponse = mapper.readValue(result.getResponse().getContentAsString(), List.class);
// Checking with dummy data to see if it is not null, if it has a quantity
		assertThat(prdctResponse).isNotNull();

	}

}
