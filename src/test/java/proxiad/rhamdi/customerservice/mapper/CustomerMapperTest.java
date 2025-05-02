package proxiad.rhamdi.customerservice.mapper;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import proxiad.rhamdi.customerservice.dto.CustomerDTO;
import proxiad.rhamdi.customerservice.entities.Customer;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



class CustomerMapperTest {

     private  CustomerMapper mapper=new CustomerMapper();
    @Test
    public void shouldMapCustomerDtoFromCustomer() {
        Customer givenCustommer=Customer.builder()
                .id(1L)
                .firstName("Rania")
                .lastName("Hamdi")
                .email("raniahamdi@gmail.com")
                .build();

        CustomerDTO expected=CustomerDTO.builder()
                .id(1L)
                .firstName("Rania")
                .lastName("Hamdi")
                .email("raniahamdi@gmail.com")
                .build();
        CustomerDTO result=this.mapper.fromCustomer(givenCustommer);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldMapCustomerFromCustomerDTO() {
        CustomerDTO givenCustomerDto=CustomerDTO.builder()
                .id(1L)
                .firstName("Rania")
                .lastName("Hamdi")
                .email("raniahamdi@gmail.com")
                .build();
        Customer expected=Customer.builder()
                .id(1L)
                .firstName("Rania")
                .lastName("Hamdi")
                .email("raniahamdi@gmail.com")
                .build();
        Customer result=this.mapper.fromCustomerDTO(givenCustomerDto);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
    @Test
    public void shouldMapListFromListCustomers(){
        List<Customer>givenCustomers=List.of(
                Customer.builder()
                        .id(1L)
                        .firstName("Rania")
                        .lastName("Hamdi")
                        .email("raniahamdi@gmail.com")
                        .build(),
                Customer.builder()
                        .id(2L)
                        .firstName("Sirine")
                        .lastName("Hamdi")
                        .email("sirinehamdi@gmail.com")
                        .build(),
                Customer.builder()
                        .id(3L)
                        .firstName("Kamel")
                        .lastName("Hamdi")
                        .email("kamelhamdi@gmail.com")
                        .build()
        );
        List<CustomerDTO>expected=List.of(
                CustomerDTO.builder()
                        .id(1L)
                        .firstName("Rania")
                        .lastName("Hamdi")
                        .email("raniahamdi@gmail.com")
                        .build(),
                CustomerDTO.builder()
                        .id(2L)
                        .firstName("Sirine")
                        .lastName("Hamdi")
                        .email("sirinehamdi@gmail.com")
                        .build(),
                CustomerDTO.builder()
                        .id(3L)
                        .firstName("Kamel")
                        .lastName("Hamdi")
                        .email("kamelhamdi@gmail.com")
                        .build()
        );
        List<CustomerDTO>result=this.mapper.fromCustomers(givenCustomers);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
    @Test
    public void shouldNotMapNullCustomer() {
        Customer givenCustommer=null;

        CustomerDTO expected=CustomerDTO.builder()
                .id(1L)
                .firstName("Rania")
                .lastName("Hamdi")
                .email("raniahamdi@gmail.com")
                .build();

        AssertionsForClassTypes.assertThatThrownBy(()->mapper.fromCustomer(givenCustommer))
                .isInstanceOf(IllegalArgumentException.class);

    }

}