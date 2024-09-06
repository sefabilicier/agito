package intern.customer.agitoo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
import intern.customer.agitoo.WebAPi.Concretes.CustomerController;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static intern.customer.agitoo.Models.enums.CustomerType.Company;
import static intern.customer.agitoo.Models.enums.CustomerType.Person;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    @InjectMocks
    private ObjectMapper objectMapper;

    @InjectMocks
    private Customer customer;

    @InjectMocks
    private CustomerDTO customerDTO;

    private Long customerId = 3L;

    @BeforeEach
    public void init(){
        customer = Customer.builder()
                .customerId (customerId)
                .customerType (Person)
                .customerAddresses (null)
                .customerDebitCards (null)
                .customerRegistration (null)
                .customerPayments (null)
                .customerPolicies (null)
                .customerContacts (null)
                .build ();
        customerDTO = CustomerDTO.builder ()
                .customerType (Company)
                .personLists (null)
                .companies (null)
                .build ();
    }

    @Test
    public void CustomerController_CustomerDetail_ReturnCustomerDto () throws Exception {
        when(customerService.getAll ()).thenReturn((List<CustomerDTO>) customerDTO);

        ResultActions response = mockMvc.perform(get ("/api/customer/get-all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.customerType", CoreMatchers.is(customerDTO.getCustomerType ())))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.companies", CoreMatchers.is (null)))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.personList", CoreMatchers.is (null)));
    }

    @Test
    public void CustomerController_AddCustomer_ReturnCreated() throws Exception {
        given(customerService.add (ArgumentMatchers.any())).willAnswer ((invocation -> invocation.getArgument (0)));

        ResultActions response = mockMvc.perform (post("/api/customer/add")
                .contentType (MediaType.APPLICATION_JSON)
                .content (objectMapper.writeValueAsString(customerDTO)));

        response
                .andExpect (MockMvcResultMatchers.status ().isCreated ())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.customerType", CoreMatchers.is(customerDTO.getCustomerType ())))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.companies", CoreMatchers.is (null)))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.personList", CoreMatchers.is (null)));
    }

    @Test
    public void CustomerController_UpdateCustomer_ReturnCustomerDto() throws Exception {
        when(customerService.update (customerDTO)).thenReturn(customerDTO);

        ResultActions response = mockMvc.perform(put("/api/customer/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.customerType", CoreMatchers.is(customerDTO.getCustomerType ())))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.companies", CoreMatchers.is (null)))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.personList", CoreMatchers.is (null)));
    }

    @Test
    public void CustomerController_DeleteCustomerById_ReturnString() throws Exception {
        doNothing().when(customerService).deleteById (customerId);

        ResultActions response = mockMvc.perform(delete("/api/customer/delete/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
