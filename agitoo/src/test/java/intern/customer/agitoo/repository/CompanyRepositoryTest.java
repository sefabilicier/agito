package intern.customer.agitoo.repository;

import com.github.javafaker.Faker;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Models.Concretes.CompanyBranch;
import intern.customer.agitoo.Models.Concretes.CompanyFinancial;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Repository.Abstracts.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@SpringJUnitConfig
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    private Faker faker;
    @Mock
    private Customer customer;
    @Mock
    private CompanyBranch branch;
    @Mock
    private CompanyFinancial financial;

    @BeforeEach
    void setUp(){

        faker = new Faker ();

        //GIVEN
        Mockito.when (customer.getCustomerId ()).thenReturn (1L);

        List<CompanyBranch> branches = new ArrayList<> ();
        branches.add (
                branch
        );
        List<CompanyFinancial> financials = new ArrayList<> ();
        financials.add (
                financial
        );

        Company company = Company.builder ()
                .companyName (faker.company ().name ())
                .companyId (1L)
                .build ();

        companyRepository.save (company);

    }

    @Test
    void testFindById(){

        //WHEN
        Optional<Company> company = companyRepository.findById (1L);

        //THEN
        assertTrue(company.isPresent ());
        assertEquals ("Test Company",company.get ().getCompanyName ());
        //since faker data is being used - it is not going pass!

    }

}
