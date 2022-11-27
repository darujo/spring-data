package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darujo.exceptions.ResourceNotFoundException;
import ru.darujo.model.Product;
import ru.darujo.service.ProductService;

import java.util.Collection;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Collection<Product> index() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product productEdit(@PathVariable long id) {
        return productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Продукт не найден c id = " + id));
    }
    @PostMapping("/products")
    public void productEdit(@RequestParam String title,@RequestParam double price) {
        productService.createProduct(title,price);
    }
    @DeleteMapping("/products/{id}")
    public void productDelete(@PathVariable long id){
        productService.deleteProduct(id);
    }
    @GetMapping ("/productsByPriceBetween")
    public List<Product> productsByPriceBetween(@RequestParam(defaultValue = "0") double min, @RequestParam(defaultValue = "0") double max ){
        return productService.productsByPriceBetween( min, max );
    }
}