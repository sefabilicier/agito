package intern.customer.agitoo.Configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper () {
        ModelMapper modelMapper = new ModelMapper ();
        modelMapper.getConfiguration ()
                .setFieldMatchingEnabled (true) //a ModelMapper to allow PRIVATE fields to be matched:
                .setFieldAccessLevel (org.modelmapper.config.Configuration.AccessLevel.PRIVATE) //a ModelMapper to allow PROTECTED fields to be matched - My data came with null and here is why
                .setMethodAccessLevel (org.modelmapper.config.Configuration.AccessLevel.PRIVATE) //a ModelMapper to allow PROTECTED methods to be matched:
                .setSourceNamingConvention (NamingConventions.JAVABEANS_MUTATOR) //Finds eligible mutators according to JavaBeans convention
                .setMatchingStrategy (MatchingStrategies.LOOSE);

//        modelMapper.createTypeMap(Customer.class, CustomerDTO.class);
//        modelMapper.createTypeMap(Person.class, PersonDTO.class);
//        modelMapper.createTypeMap(PersonActivity.class, PersonActivityDTO.class);
//        modelMapper.createTypeMap(PersonFeedback.class, PersonFeedbackDTO.class);
//        modelMapper.createTypeMap(PersonJobLife.class, PersonJobLifeDTO.class);
//        modelMapper.createTypeMap(PersonSupportTicket.class, PersonSupportTicketDTO.class);
//        modelMapper.createTypeMap(Company.class, CompanyDTO.class);
//        modelMapper.createTypeMap(CompanyBranch.class, CompanyBranchDTO.class);
//        modelMapper.createTypeMap(CompanyFinancial.class, CompanyFinancialDTO.class);
//        modelMapper.createTypeMap(CustomerAddress.class, CustomerAddressDTO.class);
//        modelMapper.createTypeMap(CustomerAddressCountry.class, CustomerAddressCountryDTO.class);
//        modelMapper.createTypeMap(CustomerClaim.class, CustomerClaimDTO.class);
//        modelMapper.createTypeMap(CustomerContact.class, CustomerContactDTO.class);
//        modelMapper.createTypeMap(CustomerDebitCard.class, CustomerDebitCardDTO.class);
//        modelMapper.createTypeMap(CustomerPayment.class, CustomerPaymentDTO.class);
//        modelMapper.createTypeMap(CustomerPolicy.class, CustomerPolicyDTO.class);
//        modelMapper.createTypeMap(CustomerPolicyRenewal.class, CustomerPolicyRenewalDTO.class);
//        modelMapper.createTypeMap(CustomerRegistration.class, CustomerRegistrationDTO.class);
//
//        modelMapper.validate();

//        modelMapper.addMappings(new PropertyMap<Person, PersonDTO> () {
//            @Override
//            protected void configure() {
//                map().setFullName(source.getFirstName() +
//                        (source.getMiddleName() != null ? " " + source.getMiddleName() : "") +
//                        " " + source.getLastName());
//            }
//        });

        return modelMapper;

    }
}