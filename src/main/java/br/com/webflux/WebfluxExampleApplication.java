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
					.name("Apple - iPhone 11 Pro Max 64GB - Space Gray")
					.description("6.5-inch Super Retina XDR display*\n" + 
							"Triple-camera system (Ultra Wide, Wide, Telephoto)\n" + 
							"A13 Bionic chip")
					.price(new BigDecimal(("829.92"))).build()).flatMap(repository::save);

				productFlux.thenMany(repository.findAll()).subscribe(System.out::println);

		};
	}
}
