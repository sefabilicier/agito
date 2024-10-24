/*
package intern.customer.agitoo.caching;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@SpringJUnitConfig
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AllArgsConstructor
@Data
public class CustomerServiceImplCachingTest {

    @Autowired
    private final CustomerServiceImpl customerServiceTest;

    @Test
    public void customerServiceCacheTest () {

        List<Company> companyDTOList = new ArrayList<Company> ();
        List<Person> personDTOList = new ArrayList<Person> ();

        CompletableFuture<CustomerDTO> customerDTO =
                customerServiceTest.add (
                        CustomerDTO.builder ()
                                .customerType (CustomerType.Company)
                                .companies (companyDTOList)
                                .list (personDTOList)
                                .build ()
                );

        verify (customerDTO, times (1));
    }

}
*/
