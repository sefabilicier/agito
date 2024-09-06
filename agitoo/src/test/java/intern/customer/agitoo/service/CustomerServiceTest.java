package intern.customer.agitoo.service;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Repository.Abstracts.CustomerRepository;
import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static intern.customer.agitoo.Models.enums.CustomerType.Company;
import static intern.customer.agitoo.Models.enums.CustomerType.Person;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void CustomerService_CreateCustomer_ReturnsCustomerDto(){
        Long customerId = 1L;
        Customer customer = Customer.builder()
                .customerId (customerId)
                .customerType (Company)
                .build ();
        CustomerDTO customerDTO = CustomerDTO.builder ()
                .customerType (Company)
                .build ();

        when(this.customerRepository.save (Mockito.any (Customer.class))).thenReturn (customer);

        CustomerDTO savedCustomerDTO = this.customerService.add (customerDTO);

        Assertions.assertThat (savedCustomerDTO).isNotNull ();

    }

    @Test
    public void CustomerService_AddCustomer_ReturnCustomerNew() {
        Long customerId = 1L;
        Customer customer =
                Customer.builder()
                        .customerId (customerId)
                        .customerType (Person)
                        .customerAddresses (null)
                        .customerDebitCards (null)
                        .customerRegistration (null)
                        .customerPayments (null)
                        .customerPolicies (null)
                        .customerContacts (null)
                                .build ();
        CustomerDTO customerDTO =
                CustomerDTO.builder ()
                .customerType (Company)
                .personLists (null)
                .companies (null)
                .build ();
        when(customerRepository.findById(customerId)).thenReturn(Optional.ofNullable(customer));

        //doNothing().when(customerRepository).deleteById(customerId);

        CustomerDTO result = customerService.add (customerDTO);

        Assertions.assertThat(result).isNotNull();
    }
    @Test
     public void CustomerService_UpdateCustomer_ReturnCustomerUpdated(){
        Long customerId = 4L;
        Customer customer = Customer
                        .builder()
                        .customerId (customerId)
                        .customerType (Person)
                        .customerAddresses (null)
                        .customerDebitCards (null)
                        .customerRegistration (null)
                        .customerPayments (null)
                        .customerPolicies (null)
                        .customerContacts (null)
                                .build ();
         CustomerDTO customerDTO = CustomerDTO.builder ()
                 .customerType (Company)
                 .personLists (null)
                 .companies (null)
                 .build ();

         when(customerRepository.findById(customerId)).thenReturn(Optional.ofNullable(customer));
         when(customerRepository.save(customer)).thenReturn(customer);

         CustomerDTO updateReturn = customerService.update(customerDTO);

        Assertions.assertThat (updateReturn).isNotNull ();
     }

    @Test
    public void CustomerService_DeleteCustomerById_ReturnVoid() {
        Long customerId = 4L;
        Customer customer = Customer
                .builder()
                .customerId (customerId)
                .customerType (Company)
                .customerAddresses (null)
                .customerDebitCards (null)
                .customerRegistration (null)
                .customerPayments (null)
                .customerPolicies (null)
                .customerContacts (null)
                .build ();

        when(customerRepository.findById(customerId)).thenReturn(Optional.ofNullable(customer));
        doNothing().when(customerRepository).delete(customer);

        assertAll(() -> customerService.deleteById (customerId));
    }

}
