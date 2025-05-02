package proxiad.rhamdi.customerservice.services;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import proxiad.rhamdi.customerservice.dto.CustomerDTO;
import proxiad.rhamdi.customerservice.entities.Customer;
import proxiad.rhamdi.customerservice.exception.EmailAlreadyExistExceprion;
import proxiad.rhamdi.customerservice.mapper.CustomerMapper;
import proxiad.rhamdi.customerservice.repo.CustomerRepo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void saveNewCustomer() {
        CustomerDTO customerDTO=CustomerDTO.builder().firstName("Rania").lastName("hamdi").email("rania.hamdi@gmail.com").build();
        Customer customer=Customer.builder().firstName("Rania").lastName("hamdi").email("rania.hamdi@gmail.com").build();
        Customer customerSaved=Customer.builder().id(1L).firstName("Rania").lastName("hamdi").email("rania.hamdi@gmail.com").build();
        CustomerDTO expected=CustomerDTO.builder().id(1L).firstName("Rania").lastName("hamdi").email("rania.hamdi@gmail.com").build();

        Mockito.when(customerRepo.findByEmail(customerDTO.getEmail())).thenReturn(Optional.empty());
        Mockito.when(customerMapper.fromCustomerDTO(customerDTO)).thenReturn(customer);
        Mockito.when(customerRepo.save(customer)).thenReturn(customerSaved);
        Mockito.when(customerMapper.fromCustomer(customerSaved)).thenReturn(expected);
        CustomerDTO result=customerService.saveNewCustomer(customerDTO);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
    @Test
    public void ShouldNotSaveNewCustomerWhenEmailExist() {
       CustomerDTO customerDTO=CustomerDTO.builder().firstName("Rania").lastName("hamdi").email("rania.hamdi@gmail.com").build();
       Customer customer=Customer.builder().firstName("Rania").lastName("hamdi").email("rania.hamdi@gmail.com").build();
       Mockito.when(customerRepo.findByEmail(customerDTO.getEmail())).thenReturn(Optional.of(customer));
       AssertionsForClassTypes.assertThatThrownBy(()->
                       customerService.saveNewCustomer(customerDTO))
               .isInstanceOf(EmailAlreadyExistExceprion.class);
    }
    @Test
    public void ShouldGetAllCustomers() {
        List<Customer>customers=List.of(
                Customer.builder()
                        .id(1L)
                        .firstName("Sirine")
                        .lastName("Hamdi")
                        .email("sirinehamdi@gmail.com")
                        .build(),
                Customer.builder()
                        .id(2L)
                        .firstName("kamel")
                        .lastName("Hamdi")
                        .email("kamelhamdi@gmail.com")
                        .build()
        );
        List<CustomerDTO>expected=List.of(
                CustomerDTO.builder()
                        .id(1L)
                        .firstName("Sirine")
                        .lastName("Hamdi")
                        .email("sirinehamdi@gmail.com")
                        .build(),
                CustomerDTO.builder()
                        .id(2L)
                        .firstName("kamel")
                        .lastName("Hamdi")
                        .email("kamelhamdi@gmail.com")
                        .build()
        );
        Mockito.when(customerRepo.findAll()).thenReturn(customers);
        Mockito.when(customerMapper.fromCustomers(customers)).thenReturn(expected);
        List<CustomerDTO> result=customerService.getAllCustomers();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);

    }



}