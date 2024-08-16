package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Repository.Abstracts.CustomerRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public DataResult<List<Customer>> getAll () {
        List<Customer> customers = customerRepository.findAll ();
        System.out.println ("Retrieved Customers: " + customers);
        executionInfo ();
        isConnected ();
        return new SuccessDataResult<> (
                customerRepository.findAll (),
                "Customers listed!"
        );
    }

    @Override
    public Result Add (Customer entity) {
        customerRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer added!"
        );
    }

    @Override
    public Result Update (Customer entity) {
        if (customerRepository.existsById (entity.getCustomerId ())) {
            customerRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerRepository.existsById (id)) {
            customerRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer not found"
            );
        }


    }


    public void executionInfo () {
        System.out.println ("it is being executed");
    }

    public void isConnected () {
        final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
        final String JDBC_USER = "C##EGITIM";
        final String JDBC_PASSWORD = "1";

        Connection connection = null;

        try {
            Class.forName ("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection (JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println ("Successfully connected to the database!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println ("JDBC Driver not found.");
            e.printStackTrace ();
        }
    }
}
