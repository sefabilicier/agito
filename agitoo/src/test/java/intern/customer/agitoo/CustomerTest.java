package intern.customer.agitoo;

import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Repository.Abstracts.CustomerRepository;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@Data
@AllArgsConstructor
public class CustomerTest {

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
        assertThat(savedCustomer.getCustomerAddresses().getFirst ().getCustomer()).isEqualTo(savedCustomer);
    }
}