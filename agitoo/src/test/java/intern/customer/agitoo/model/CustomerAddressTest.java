package intern.customer.agitoo.model;

import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressRepository;
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
public class CustomerAddressTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Test
    public void testCreateCustomerAndAddress() {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.Company);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerAddress address = new CustomerAddress();
        address.setAddressLine1("123 Main St");
        address.setCustomer(savedCustomer);

        // Act
        CustomerAddress savedAddress = customerAddressRepository.save(address);

        // Assert
        assertThat(savedAddress).isNotNull();
        assertThat(savedAddress.getAddressID()).isGreaterThan(0);
        assertThat(savedAddress.getAddressLine1()).isEqualTo("123 Main St");
        assertThat(savedAddress.getCustomer()).isEqualTo(savedCustomer);
    }
}
