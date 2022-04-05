package com.beTheDonor.controller;

import com.beTheDonor.controller.requestbody.ProductRequest;
import com.beTheDonor.entity.Product;
import com.beTheDonor.service.AdminService;
import com.beTheDonor.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(controllers = AdminController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ContextConfiguration(locations = "/test-context.xml")
class AdminControllerTest {
    // creating a mock object of the class ProductRepository
    @Mock
    private ProductService productService;

    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    // calling junit - injecting the mock object in ProductController class
    @MockBean
    ProductController controller;

    //	setup function will execute before the execution of every test case
    @BeforeEach
    public void setup() {
//    	Receiving the controller objects as parameters, building MOCKMvc instance by registering the controller instance
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetProducts() throws Exception {
        List<Product> listProduct = new ArrayList<Product>();
        Product product = new Product();
        product.setProductName("Dummy");
        product.setPrice(200.0d);
        listProduct.add(product);
//		Checking with dummy data, if able to get the products, then returning the list of products
        doReturn(listProduct).when(productService).getProducts();
//      Creating request builder to make request
//		mapper converts JSON object to JSON String
//		Calling API
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json"));

//  API value is got
        mockMvc.perform(requestBuilder);
    }

}