package br.com.webflux.resource;

import br.com.webflux.domain.Product;
import br.com.webflux.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ProductResource {


    private ProductService service;

    public ProductResource(ProductService service){
        this.service = service;
    }

    @GetMapping("/products")
    public Flux<Product> getAllProducts(){
        return this.service.findAll();
    }

    @GetMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> findProductById(@PathVariable final String id){
        return this.service.findProductById(id).map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody final Product product){
        return this.service.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable(value = "id") final String id, @RequestBody Product product){
        return this.service.updateProduct(id, product).map(product1 -> ResponseEntity.ok(product)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
