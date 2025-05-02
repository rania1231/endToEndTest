package proxiad.rhamdi.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import proxiad.rhamdi.customerservice.entities.Customer;
import proxiad.rhamdi.customerservice.repo.CustomerRepo;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    @Profile("!test")// On va pas le créer lorsque on fait nos tests sinon on va dupliquer nos données
    CommandLineRunner commandLineRunner(CustomerRepo customerRepo) {
        return args -> {
            customerRepo.save(Customer.builder()
                            .firstName("Rania")
                            .lastName("Hamdi")
                    .email("raniahamdi@gmail.com")
                    .build());
            customerRepo.save(Customer.builder()
                    .firstName("Sirine")
                    .lastName("Hamdi")
                    .email("sirinehamdi@gmail.com")
                    .build());
            customerRepo.save(Customer.builder()
                    .firstName("kamel")
                    .lastName("Hamdi")
                    .email("kamelhamdi@gmail.com")
                    .build());
        };
    }
}
