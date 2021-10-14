package br.univesp.meuconsumo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MeuconsumoApplication {
    
    @Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(MeuconsumoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	// @Bean
    // CommandLineRunner init(ContactRepository repository) {
    //     return args -> {
    //         repository.deleteAll();
    //         LongStream.range(1, 11)
    //                 .mapToObj(i -> {
    //                     Contact c = new Contact();
    //                     c.setName("Contact " + i);
    //                     c.setEmail("contact" + i + "@email.com");
    //                     c.setPhone("(111) 111-1111");
    //                     return c;
    //                 })
    //                 .map(v -> repository.save(v))
    //                 .forEach(System.out::println);
    //     };
    // }
}