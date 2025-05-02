package proxiad.rhamdi.customerservice.repo;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;
import proxiad.rhamdi.customerservice.entities.Customer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class CustomerRepoTest {
    @Autowired
    private CustomerRepo customerRepo;
    @BeforeEach //c'est à dire que avant chaque test on va appliquer cette methode (insérer 3 lignes)
    public void setUp(){
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
    }
    @Test
    public void shouldFindCustomerByEmail() {
        String givenEmail = "raniahamdi@gmail.com";
        Optional<Customer> result= customerRepo.findByEmail(givenEmail);
        assertThat(result).isPresent();
    }

    @Test
    public void shouldNotFindCustomerByEmail() {
        String givenEmail = "xxxx@gmail.com";
        Optional<Customer> result= customerRepo.findByEmail(givenEmail);
        assertThat(result).isEmpty();
    }
    @Test
    public void shouldFindCustomerByFirstName() {
        String keyword = "e";
        List<Customer>expected=List.of(
                Customer.builder()
                        .firstName("Sirine")
                        .lastName("Hamdi")
                        .email("sirinehamdi@gmail.com")
                        .build(),
                Customer.builder()
                        .firstName("kamel")
                        .lastName("Hamdi")
                        .email("kamelhamdi@gmail.com")
                        .build()
        );
        List<Customer> result= customerRepo.findByFirstNameContainingIgnoreCase(keyword);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expected.size());
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);

    }

}