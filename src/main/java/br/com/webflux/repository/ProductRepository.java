package br.com.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.webflux.domain.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
