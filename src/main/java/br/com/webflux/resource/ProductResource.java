package br.com.webflux.resource;

import br.com.webflux.domain.Product;
import br.com.webflux.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ProductResource {

    private ProductService service;
	
	@Autowired
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
    
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/products/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") final String id){
        return this.service.deleteProduct(id).then(Mono.just(ResponseEntity.ok().<Void>build())
        		.defaultIfEmpty(ResponseEntity.notFound().build()));
    }
    
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/products")
    public Mono<Void> deleteAll(){
        return this.service.deleteAllProducts();
    }
    
    
}
