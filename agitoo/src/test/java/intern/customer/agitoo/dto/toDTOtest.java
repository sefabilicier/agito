package intern.customer.agitoo.dto;

import intern.customer.agitoo.Configuration.MapperConfiguration;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Models.enums.MaritalStatus;
import intern.customer.agitoo.Models.enums.PersonGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Data
@AllArgsConstructor
public class toDTOtest {

    private final ModelMapper modelMapper = new MapperConfiguration ().modelMapper ();

    @Test
    public void testCustomerToDTO(){
        Customer customer = new Customer ();
        customer.setCustomerType (CustomerType.Person);

        CustomerDTO customerDTO = modelMapper.map (customer, CustomerDTO.class);

        assertNotNull(customerDTO);
        assertEquals(CustomerType.Person, customerDTO.getCustomerType ());
    }

    @Test
    public void testPersonToDTO(){
        Person person = new Person ();
        person.setPersonId (1L);
        person.setDateOfBirth (LocalDate.of (2024,8,22));
        person.setGender (PersonGender.Male);
        person.setFirstName ("Martijn");
        person.setMiddleName ("");
        person.setLastName ("Garritsen");
        person.setMaritalStatus (MaritalStatus.Married);
        person.setNationality ("Holland");
        person.setOccupation ("Artist");


        PersonDTO personDTO = modelMapper.map (person, PersonDTO.class);

        assertNotNull(personDTO);
        assertEquals(person, personDTO);
    }
}