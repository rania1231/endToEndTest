package proxiad.rhamdi.customerservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import proxiad.rhamdi.customerservice.dto.CustomerDTO;
import proxiad.rhamdi.customerservice.entities.Customer;
import proxiad.rhamdi.customerservice.services.CustomerService;

import java.util.List;


@ActiveProfiles("test")
@WebMvcTest(CustomerRestController.class)
class CustomerRestControllerTest {
    @MockitoBean
    private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    List<CustomerDTO> customersDTO;

    @BeforeEach
    void setUp(){
        this.customersDTO=List.of(
                CustomerDTO.builder().id(1L).firstName("Rania").lastName("hamdi").email("rania.hamdi@gmail.com").build(),
                CustomerDTO.builder().id(2L).firstName("Sirina").lastName("hamdi").email("sirine.hamdi@gmail.com").build(),
                CustomerDTO.builder().id(3L).firstName("Kamel").lastName("hamdi").email("kamel.hamdi@gmail.com").build()
        );
    }

    @Test
    void shouldGetAllCustomers() throws Exception {
        Mockito.when(customerService.getAllCustomers()).thenReturn(this.customersDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customersDTO)));
    }
    @Test
    void shouldGetCustomerById() throws Exception {
        Long id=1L;
        Mockito.when(customerService.getCustomerById(id)).thenReturn(this.customersDTO.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(this.customersDTO.get(0))));
    }
    @Test
    void shouldSearchCustomers()throws Exception{
        String keyword="a";
        Mockito.when(customerService.searchCustomer(keyword)).thenReturn(this.customersDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/search?keyword="+keyword))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customersDTO)));
    }
    @Test
    void shouldSaveCustomer()throws Exception{
        CustomerDTO customerDTO=this.customersDTO.get(0);
        String expected= """
                {
                "id": 1,"firstName": "Rania","lastName": "hamdi","email": "rania.hamdi@gmail.com"
                }
                """;
        Mockito.when(customerService.saveNewCustomer(Mockito.any())).thenReturn(this.customersDTO.get(0));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }
    @Test
    void shouldUpdateCustomer()throws Exception{
        Long id=1L;
        CustomerDTO customerDTO=this.customersDTO.get(0);
        Mockito.when(customerService.updateCustomer(Mockito.eq(id),Mockito.any())).thenReturn(this.customersDTO.get(0));
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/customers/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customerDTO))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customersDTO.get(0))));
    }
    @Test
    void shouldDeleteCustomer()throws Exception{
        Long id=1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}