package ru.darujo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.darujo.model.Product;
import ru.darujo.repository.ProductRepository;

import java.util.*;

@Service
@Primary
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Collection<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public void createProduct(String title, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public List<Product> productsByPriceBetween(double min, double max ){
        return productRepository.findByPriceBetween( min, max == 0 ? Double.MAX_VALUE : max  );
    }
}
