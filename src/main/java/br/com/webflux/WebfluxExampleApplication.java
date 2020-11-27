package br.com.webflux;

import br.com.webflux.domain.Product;
import br.com.webflux.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@SpringBootApplication
public class WebfluxExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxExampleApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ReactiveMongoOperations operations, ProductRepository repository){
		return args -> {
			Flux<Product> productFlux = Flux.just(Product.builder()
					.name("iPhone XS Apple 256GB Dourado 5,8” 12MP - iOS")
					.description("O iPhone XS tem tela Super Retina de 5,8 polegadas")
					.price(new BigDecimal(("4.859"))).build()).flatMap(repository::save);

				productFlux.thenMany(repository.findAll()).subscribe(System.out::println);

		};
	}
}
