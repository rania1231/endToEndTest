package proxiad.rhamdi.customerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proxiad.rhamdi.customerservice.entities.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByEmail(String email);
    List<Customer> findByFirstNameContainingIgnoreCase(String lastName);

}
