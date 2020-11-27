package br.com.webflux.service;

import br.com.webflux.domain.Product;
import br.com.webflux.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Flux<Product> findAll() {
        return this.repository.findAll();
    }

    public Mono<Product> findProductById(final String id) {
        return this.repository.findById(id);
    }

    public Mono<Product> createProduct(final Product product){
       return this.repository.save(product);
    }

    public Mono<Product> updateProduct(final String id, final Product product) {

        return this.repository.findById(id).flatMap(myProduct -> {
            myProduct.setName(product.getName());
            myProduct.setDescription(product.getDescription());
            myProduct.setPrice(product.getPrice());
            return repository.save(myProduct);
        });
    }
}
