package proxiad.rhamdi.customerservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proxiad.rhamdi.customerservice.dto.CustomerDTO;
import proxiad.rhamdi.customerservice.entities.Customer;
import proxiad.rhamdi.customerservice.exception.CustomerNotFoundException;
import proxiad.rhamdi.customerservice.exception.EmailAlreadyExistExceprion;
import proxiad.rhamdi.customerservice.mapper.CustomerMapper;
import proxiad.rhamdi.customerservice.repo.CustomerRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerMapper customerMapper;
    private CustomerRepo customerRepo;
    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistExceprion {
        log.info("Saving new customer ==>{}", customerDTO.toString());
        Optional<Customer> customer = customerRepo.findByEmail(customerDTO.getEmail());
        if(!customer.isEmpty()) {
            log.info("Customer already exist");
            throw new EmailAlreadyExistExceprion("Email already exist");
        }else{
            return this.customerMapper.fromCustomer(
                    this.customerRepo.save(
                            this.customerMapper.fromCustomerDTO(customerDTO)
                    )
            );
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return this.customerMapper.fromCustomers(customers);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isEmpty()) {
            return this.customerMapper.fromCustomer(customer.get());
        }
        else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public List<CustomerDTO> searchCustomer(String keyword) {
        return this.customerRepo.findByFirstNameContainingIgnoreCase(keyword)
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isEmpty()) {
            customerDTO.setId(id);
            return this.customerMapper.fromCustomer(
                    this.customerRepo.save(
                            customerMapper.fromCustomerDTO(customerDTO)
                    )
            );

        }
        else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isEmpty()) {
            customerRepo.deleteById(id);
        }else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }
}
