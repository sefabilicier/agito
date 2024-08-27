package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerDebitCardDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerDebitCardMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerDebitCard;
import intern.customer.agitoo.Repository.Abstracts.CustomerDebitCardRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerDebitCardService;
import intern.customer.agitoo.Service.Rules.CommonBusinessRules;
import intern.customer.agitoo.Service.Rules.toDatabase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDebitCardServiceImpl implements ICustomerDebitCardService {

    @Autowired
    private CustomerDebitCardRepository customerDebitCardRepository;

    @Autowired
    private CustomerDebitCardMapper customerDebitCardMapper;

    @Override
    @Cacheable(value = "customer-debit-card")
    public List<CustomerDebitCardDTO> getAll () {
        toDatabase.isConnected ();
        List<CustomerDebitCard> customerDebitCards = customerDebitCardRepository.findAll ();
        List<CustomerDebitCardDTO> customerDebitCardDTOS = customerDebitCards
                .stream ()
                .map (customerDebitCard ->
                        customerDebitCardMapper.toDTO (customerDebitCard, CustomerDebitCardDTO.class))
                .collect(Collectors.toList ());

        return customerDebitCardDTOS;

    }

    @Override
    @CachePut(value = "customer-debit-card", key = "")
    public CustomerDebitCardDTO add (CustomerDebitCardDTO dtoModel) {
        CustomerDebitCard customerDebitCard = customerDebitCardMapper
                .toEntity (dtoModel, CustomerDebitCard.class);
        CustomerDebitCard savedCustomerDebitCard = customerDebitCardRepository.save(customerDebitCard);
        return customerDebitCardMapper
                .toDTO (savedCustomerDebitCard, CustomerDebitCardDTO.class);
    }

    @Override
    @CachePut(value = "customer-debit-card", key = "")
    public CustomerDebitCardDTO update (CustomerDebitCardDTO dtoModel) {
        CustomerDebitCard customerDebitCard = customerDebitCardMapper.toEntity (dtoModel,CustomerDebitCard.class);
        CustomerDebitCard updatedCustomerDebitCard = customerDebitCardRepository.save (customerDebitCard);
        return customerDebitCardMapper.toDTO (updatedCustomerDebitCard, CustomerDebitCardDTO.class);
    }

    @Override
    @CacheEvict(value = "customer-debit-card", key = "#id")
    public void deleteById (Long id) {
        CommonBusinessRules.checkIfIdExist (customerDebitCardRepository, id);
        customerDebitCardRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
