package com.beTheDonor.controller;

import com.beTheDonor.controller.requestbody.ProductRequest;
import com.beTheDonor.controller.requestbody.RegistrationRequest;
import com.beTheDonor.entity.ApplicationUser;
import com.beTheDonor.entity.Product;
import com.beTheDonor.service.AdminService;
import com.beTheDonor.service.ProductService;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    ProductService productService;
    AdminService as;
    @GetMapping("/api/v1/admin")
    public String adminDashboard(Model model)
    {
        List<Product> products = productService.getProducts();
        model.addAttribute("products",products);

        List<ApplicationUser> users = as.getUsers();
        model.addAttribute("users", users);

        List<ApplicationUser> patients = as.getPatients();
        model.addAttribute("patients", patients);

        List<ApplicationUser> riders = as.getRiders();
        model.addAttribute("riders", riders);

        List<ApplicationUser> donors = as.getDonors();
        model.addAttribute("donors", donors);

        model.addAttribute("product",new ProductRequest());
        return "admindashboard";
    }

//    @PostMapping()
////	ResponseEntity class<T> which extends HttpClass<T>
//    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
////		adds the HTTPStatus code
////		Calling the addQuantity method of the interface ProductService
//        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
//    }
    @GetMapping("/api/v1/admin/addProductsInTable")
    public String addProducts(@ModelAttribute ProductRequest productRequest)
    {
        as.add(productRequest);
        return "redirect:/admindashboard";
    }

    @GetMapping("/api/v1/admin/deleteProduct/{id}")
    @ResponseBody
        public void delete(@PathVariable long id)
    {
        System.out.println(id);
        as.delete(id);
//        //System.out.println("PRODUCTID:::::::::"+productRequest.getProductName());
    }

    @GetMapping("/api/v1/admin/updateProductsInTable/{qty}/{price}/{id}")
    public String updateProducts(@PathVariable int qty, @PathVariable double price, @PathVariable long id )
    {
        as.update(qty, price, id);
        return "redirect:/admindashboard";
    }
}
