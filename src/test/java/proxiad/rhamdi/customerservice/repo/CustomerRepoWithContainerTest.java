//package proxiad.rhamdi.customerservice.repo;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.springframework.test.context.ActiveProfiles;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import proxiad.rhamdi.customerservice.entities.Customer;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Testcontainers
//@ActiveProfiles("test")
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//ne pas remplacer le datasourse de postgresql(celui du container )par h2(globalement configuré)
//class CustomerRepoWithContainerTest {
//    @Container
//    @ServiceConnection
//    private static PostgreSQLContainer postgreSQLContainer= new PostgreSQLContainer("postgres:16");
//    @Autowired
//    CustomerRepo customerRepo;
//
//    @BeforeEach //c'est à dire que avant chaque test on va appliquer cette methode (insérer 3 lignes)
//    public void setUp(){
//        customerRepo.save(Customer.builder()
//                .firstName("Rania")
//                .lastName("Hamdi")
//                .email("raniahamdi@gmail.com")
//                .build());
//        customerRepo.save(Customer.builder()
//                .firstName("Sirine")
//                .lastName("Hamdi")
//                .email("sirinehamdi@gmail.com")
//                .build());
//        customerRepo.save(Customer.builder()
//                .firstName("kamel")
//                .lastName("Hamdi")
//                .email("kamelhamdi@gmail.com")
//                .build());
//    }
//    @Test
//    public void connectionEstablishedTest(){
//        assertThat(postgreSQLContainer.isCreated()).isTrue();
//        assertThat(postgreSQLContainer.isRunning()).isTrue();
//    }
//
//    @Test
//    public void shouldFindCustomerByEmail() {
//        String givenEmail = "raniahamdi@gmail.com";
//        Optional<Customer> result= customerRepo.findByEmail(givenEmail);
//        assertThat(result).isPresent();
//    }
//
//    @Test
//    public void shouldNotFindCustomerByEmail() {
//        String givenEmail = "xxxx@gmail.com";
//        Optional<Customer> result= customerRepo.findByEmail(givenEmail);
//        assertThat(result).isEmpty();
//    }
//    @Test
//    public void shouldFindCustomerByFirstName() {
//        String keyword = "e";
//        List<Customer> expected=List.of(
//                Customer.builder()
//                        .firstName("Sirine")
//                        .lastName("Hamdi")
//                        .email("sirinehamdi@gmail.com")
//                        .build(),
//                Customer.builder()
//                        .firstName("kamel")
//                        .lastName("Hamdi")
//                        .email("kamelhamdi@gmail.com")
//                        .build()
//        );
//        List<Customer> result= customerRepo.findByFirstNameContainingIgnoreCase(keyword);
//        assertThat(result).isNotNull();
//        assertThat(result.size()).isEqualTo(expected.size());
//        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
//
//    }
//
//}