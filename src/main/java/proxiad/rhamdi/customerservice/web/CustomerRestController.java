package proxiad.rhamdi.customerservice.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import proxiad.rhamdi.customerservice.dto.CustomerDTO;
import proxiad.rhamdi.customerservice.entities.Customer;
import proxiad.rhamdi.customerservice.exception.CustomerNotFoundException;
import proxiad.rhamdi.customerservice.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerRestController {
    private CustomerService customerService;


    @GetMapping("/customers")
   public List<CustomerDTO> getAllCustomers() {
       return this.customerService.getAllCustomers();
   }
   @GetMapping("/customers/{id}")
   public CustomerDTO getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
       return this.customerService.getCustomerById(id);
   }
   @GetMapping("/customers/search")
   public List<CustomerDTO>searchCustomer(@RequestParam String keyword) {
       return this.customerService.searchCustomer(keyword);
   }
   @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return this.customerService.saveNewCustomer(customerDTO);
   }

   @PutMapping("/customers/{id}")
   public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody  CustomerDTO customerDTO) {
        return this.customerService.updateCustomer(id, customerDTO);
   }
   @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
   }
}
