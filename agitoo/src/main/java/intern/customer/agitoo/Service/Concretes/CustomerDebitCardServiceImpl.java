package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerDebitCardDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerDebitCardMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerDebitCard;
import intern.customer.agitoo.Repository.Abstracts.CustomerDebitCardRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerDebitCardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static intern.customer.agitoo.Service.Rules.CommonBusinessRules.checkIfIdExist;
import static intern.customer.agitoo.Service.Rules.toDatabase.isConnected;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDebitCardServiceImpl implements ICustomerDebitCardService {

    @Autowired
    private CustomerDebitCardRepository customerDebitCardRepository;

    @Autowired
    private CustomerDebitCardMapper customerDebitCardMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-debit-card")
    public CompletableFuture<List<CustomerDebitCardDTO>> getAll () {
        isConnected ();
        List<CustomerDebitCard> customerDebitCards = customerDebitCardRepository.findAll ();
        List<CustomerDebitCardDTO> customerDebitCardDTOS = customerDebitCards
                .stream ()
                .map (customerDebitCard ->
                        customerDebitCardMapper.toDTO (customerDebitCard, CustomerDebitCardDTO.class))
                .collect (Collectors.toList ());

        return CompletableFuture.completedFuture (customerDebitCardDTOS);

    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-debit-card", key = "#result.debitCardID")
    public CompletableFuture<CustomerDebitCardDTO> add (CustomerDebitCardDTO dtoModel) {
        CustomerDebitCard customerDebitCard = customerDebitCardMapper
                .toEntity (dtoModel, CustomerDebitCard.class);
        CustomerDebitCard savedCustomerDebitCard = customerDebitCardRepository.save (customerDebitCard);
        return CompletableFuture.completedFuture (customerDebitCardMapper
                .toDTO (savedCustomerDebitCard, CustomerDebitCardDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-debit-card", key = "#result.debitCardID")
    public CompletableFuture<CustomerDebitCardDTO> update (CustomerDebitCardDTO dtoModel) {
        CustomerDebitCard customerDebitCard = customerDebitCardMapper.toEntity (dtoModel, CustomerDebitCard.class);
        CustomerDebitCard updatedCustomerDebitCard = customerDebitCardRepository.save (customerDebitCard);
        return CompletableFuture.completedFuture (customerDebitCardMapper.toDTO (updatedCustomerDebitCard, CustomerDebitCardDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-debit-card", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerDebitCardRepository, id);
        customerDebitCardRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-debit-card", key = "#id")
    public CompletableFuture<CustomerDebitCardDTO> findById (Long id) {
        checkIfIdExist (customerDebitCardRepository, id);
        CustomerDebitCard customerDebitCard = customerDebitCardRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer debit card not found with id: " + id));
        return CompletableFuture.completedFuture (customerDebitCardMapper.toDTO (customerDebitCard, CustomerDebitCardDTO.class));
    }
}
