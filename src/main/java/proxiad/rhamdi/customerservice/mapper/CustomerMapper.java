package proxiad.rhamdi.customerservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proxiad.rhamdi.customerservice.dto.CustomerDTO;
import proxiad.rhamdi.customerservice.entities.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();
    public CustomerDTO fromCustomer(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public List<CustomerDTO> fromCustomers(List<Customer> customers) {
        return customers.stream().map(this::fromCustomer).collect(Collectors.toList());
    }

}
