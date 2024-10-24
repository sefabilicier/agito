package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CompanyFinancialDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyFinancialMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CompanyFinancial;
import intern.customer.agitoo.Repository.Abstracts.CompanyFinancialRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyFinancialService;
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
public class CompanyFinancialServiceImpl implements ICompanyFinancialService {

    @Autowired
    private CompanyFinancialRepository companyFinancialRepository;

    @Autowired
    private CompanyFinancialMapper companyFinancialMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-financial")
    public CompletableFuture<List<CompanyFinancialDTO>> getAll () {
        isConnected ();
        List<CompanyFinancial> companyFinancials = companyFinancialRepository.findAll ();

        List<CompanyFinancialDTO> companyFinancialDTOS = companyFinancials
                .stream ()
                .map (companyFinancial -> companyFinancialMapper
                        .toDTO (companyFinancial, CompanyFinancialDTO.class))
                .collect (Collectors.toList ());

        return CompletableFuture.completedFuture (companyFinancialDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-financial", key = "#result.financialID")
    public CompletableFuture<CompanyFinancialDTO> add (CompanyFinancialDTO dtoModel) {
        CompanyFinancial companyFinancial = companyFinancialMapper
                .toEntity (dtoModel, CompanyFinancial.class);

        CompanyFinancial savedCompanyBranchDTO = companyFinancialRepository.save (companyFinancial);
        return CompletableFuture.completedFuture (companyFinancialMapper.toDTO (savedCompanyBranchDTO, CompanyFinancialDTO.class));

    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-financial", key = "#result.financialID")
    public CompletableFuture<CompanyFinancialDTO> update (CompanyFinancialDTO dtoModel) {
        CompanyFinancial companyFinancial = companyFinancialMapper
                .toEntity (dtoModel, CompanyFinancial.class);

        CompanyFinancial updatedCompanyFinancial = companyFinancialRepository.save (companyFinancial);
        return CompletableFuture.completedFuture (companyFinancialMapper.toDTO (updatedCompanyFinancial, CompanyFinancialDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-financial", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (companyFinancialRepository, id);
        companyFinancialRepository.deleteById (id);
        System.out.println (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "company-financial", key = "#id")
    public CompletableFuture<CompanyFinancialDTO> findById (Long id) {
        checkIfIdExist (companyFinancialRepository, id);
        CompanyFinancial companyFinancial = companyFinancialRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Branch not found with id: " + id));
        return CompletableFuture.completedFuture (companyFinancialMapper.toDTO (companyFinancial, CompanyFinancialDTO.class));
    }
}
