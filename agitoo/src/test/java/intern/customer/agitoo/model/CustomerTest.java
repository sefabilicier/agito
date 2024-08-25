package intern.customer.agitoo.model;

import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Repository.Abstracts.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@SpringJUnitConfig
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCreateCustomer(){
        Customer customer = new Customer ();
        customer.setCustomerType(CustomerType.Person);

        Customer savedCustomer = customerRepository.save (customer);

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getCustomerId()).isGreaterThan(0);

    }

    @Test
    public void testCustomerWithAddresses() {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.Company);
        CustomerAddress address = new CustomerAddress();
        address.setCustomer(customer);
        customer.getCustomerAddresses().add(address);

        // Act
        Customer savedCustomer = customerRepository.save(customer);

        // Assert
        assertThat(savedCustomer.getCustomerAddresses()).isNotEmpty();
        assertThat(savedCustomer.getCustomerAddresses()).isEqualTo(savedCustomer);
    }
}