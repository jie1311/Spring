package applications;

import entities.Type;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import repositories.TypeRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"repositories"})
public class Application implements CommandLineRunner {

	@Autowired
	private TypeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		repository.deleteAll();
//
//		repository.save(new Type("Boeing", "777", "9X"));
//		repository.save(new Type("Boeing", "777", "300ER"));
//		repository.save(new Type("Boeing", "787", "10"));
//		repository.save(new Type("Airbus", "A350", "900ULR"));

		// fetch all customers
		for (Type type : repository.findAll()) {
			System.out.println(type.getType());
		}
		System.out.println();

		for (Type type : repository.findByManufacturer("Boeing")) {
			System.out.println(type.getType());
		}
		System.out.println();

	}
}
