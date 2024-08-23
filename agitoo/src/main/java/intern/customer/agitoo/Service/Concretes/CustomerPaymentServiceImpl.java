package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerPaymentMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerPayment;
import intern.customer.agitoo.Repository.Abstracts.CustomerPaymentRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerPaymentService;
import intern.customer.agitoo.Service.Rules.toDatabase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPaymentServiceImpl implements ICustomerPaymentService {

    @Autowired
    private CustomerPaymentRepository customerPaymentRepository;

    @Autowired
    private CustomerPaymentMapper customerPaymentMapper;

    @Override
    public List<CustomerPaymentDTO> getAll () {
        toDatabase.isConnected ();
        List<CustomerPayment> customerPayments = customerPaymentRepository.findAll ();
        List<CustomerPaymentDTO> customerPaymentDTOS = customerPayments
                .stream ()
                .map (customerPayment -> customerPaymentMapper
                        .toDTO (customerPayment, CustomerPaymentDTO.class))
                .collect(Collectors.toList());
        return customerPaymentDTOS;
    }

    @Override
    public CustomerPaymentDTO add (CustomerPaymentDTO dtoModel) {
        CustomerPayment customerPayment = customerPaymentMapper.toEntity (dtoModel, CustomerPayment.class );
        CustomerPayment savedCustomerPayment = customerPaymentRepository.save (customerPayment);
        return customerPaymentMapper.toDTO (savedCustomerPayment, CustomerPaymentDTO.class);
    }

    @Override
    public CustomerPaymentDTO update (CustomerPaymentDTO dtoModel) {
        CustomerPayment customerPayment = customerPaymentMapper.toEntity (dtoModel, CustomerPayment.class);
        CustomerPayment updatedCustomerPayment = customerPaymentRepository.save (customerPayment);
        return customerPaymentMapper.toDTO (updatedCustomerPayment, CustomerPaymentDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        customerPaymentRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
