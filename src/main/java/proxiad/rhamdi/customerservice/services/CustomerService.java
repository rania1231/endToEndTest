package proxiad.rhamdi.customerservice.services;

import org.springframework.stereotype.Service;
import proxiad.rhamdi.customerservice.dto.CustomerDTO;
import proxiad.rhamdi.customerservice.exception.CustomerNotFoundException;
import proxiad.rhamdi.customerservice.exception.EmailAlreadyExistExceprion;

import java.util.List;


public interface CustomerService {
     CustomerDTO saveNewCustomer(CustomerDTO customerDTO)  throws EmailAlreadyExistExceprion;
     List<CustomerDTO> getAllCustomers();
     CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException;
     List<CustomerDTO>searchCustomer(String keyword);
     CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException;
     void deleteCustomer(Long id) throws CustomerNotFoundException;
}
