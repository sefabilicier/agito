package intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Service.NotificationCenter.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationRule {

    @Autowired
    private NotificationManager notificationManager;


    public final String recipientCompany = CustomerType.Company.toString ();
    public final String recipientPerson = CustomerType.Person.toString ();

    public void sendNotification (
            CustomerDTO customerDTO,
            PersonDTO personDTO,
            CompanyDTO companyDTO) {

        final String messageForPerson = String.format ("Dear %s! we are happy to have you. Thanks, agito", personDTO.getFullName ());
        final String messageForCompany = String.format ("Dear %s! we are happy to have you. Thanks, agito", companyDTO.getCompanyName ());


        if (CustomerType.Person.equals (customerDTO.getCustomerType ())) {
            notificationManager.notify (
                    recipientPerson,
                    messageForPerson);
        } else if (CustomerType.Company.equals (customerDTO.getCustomerType ())) {
            notificationManager.notify (
                    recipientCompany,
                    messageForCompany
            );
        } else {
            sendErrorNotification (customerDTO, personDTO, companyDTO);
        }
    }

    public void sendErrorNotification (
            CustomerDTO customerDTO,
            PersonDTO personDTO,
            CompanyDTO companyDTO) {

        final String errorMessageForPerson = String.format (
                "Dear %s! we could've not got your registration down. " +
                        "Please contact our team with this mail!  Thanks, agito", personDTO.getFullName ());
        final String errorMessageForCompany = String.format (
                "Dear %s! we could've not got your registration down. " +
                        "Please contact our team with this mail!  Thanks, agito", companyDTO.getCompanyName ());

        if (CustomerType.Person.equals (customerDTO.getCustomerType ())) {
            notificationManager.notify (
                    recipientPerson,
                    errorMessageForPerson);
        } else if (CustomerType.Company.equals (customerDTO.getCustomerType ())) {
            notificationManager.notify (
                    recipientCompany,
                    errorMessageForCompany);
        }
    }

}
