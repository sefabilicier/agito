package intern.customer.agitoo.repository;

import com.github.javafaker.Faker;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.MaritalStatus;
import intern.customer.agitoo.Models.enums.PersonGender;
import intern.customer.agitoo.Repository.Abstracts.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@SpringJUnitConfig
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void PersonRepository_SaveAll_ReturnsSavedPerson(){

        Faker faker = new Faker ();

        //Arrange
        Person person = Person.builder()
                .personId (1L)
                .firstName (faker.name ().toString ())
                .middleName ("")
                .lastName ("Though")
                .gender (PersonGender.Male)
                .dateOfBirth (LocalDate.of(2005, 12, 25))
                .maritalStatus (MaritalStatus.Single)
                .nationality ("Swedish")
                .occupation ("Sweden")
                .build ();

        //Act
        Person savedPerson = personRepository.save (person);

        //Asser
        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson.getMiddleName ().isEmpty ());

    }

    @Test
    public void PersonRepository_SaveAll_ReturnsMoreThenOne(){

        Faker faker = new Faker ();

        //Arrange
        Person person = Person.builder()
                .personId (1L)
                .firstName (faker.name ().toString ())
                .middleName ("")
                .lastName ("Though")
                .gender (PersonGender.Male)
                .dateOfBirth (LocalDate.of(2005, 12, 25))
                .maritalStatus (MaritalStatus.Single)
                .nationality ("Swedish")
                .occupation ("Sweden")
                .build ();
        //Arrange
        Person person2 = Person.builder()
                .personId (1L)
                .firstName (faker.name ().toString ())
                .middleName ("")
                .lastName ("Though")
                .gender (PersonGender.Male)
                .dateOfBirth (LocalDate.of(2005, 12, 25))
                .maritalStatus (MaritalStatus.Single)
                .nationality ("Swedish")
                .occupation ("Sweden")
                .build ();

        //Act
        Person savedPerson = personRepository.save (person);
        Person savedPerson2 = personRepository.save (person2);

        List<Person> personList = personRepository.findAll();

        //Asser
        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson2).isNotNull();
        Assertions.assertThat (personList).isNotNull ();
        Assertions.assertThat (personList.size ()).isEqualTo (2);

    }


}
