package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Repository.Abstracts.CompanyRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyService;
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
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "company")
    public CompletableFuture<List<CompanyDTO>> getAll () {
        isConnected ();
        List<Company> companies = companyRepository.findAll ();
        List<CompanyDTO> companyDTOS = companies
                .stream ()
                .map (company -> companyMapper.toDTO (company, CompanyDTO.class))
                .collect (Collectors.toList ());

        return CompletableFuture.completedFuture (companyDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "company", key = "#result.companyId")
    public CompletableFuture<CompanyDTO> add (CompanyDTO dtoModel) {
        Company company = companyMapper
                .toEntity (dtoModel, Company.class);
        Company savedCompany = companyRepository.save (company);
        return CompletableFuture.completedFuture (companyMapper.toDTO (savedCompany, CompanyDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "company", key = "#result.companyId")
    public CompletableFuture<CompanyDTO> update (CompanyDTO dtoModel) {
        Company company = companyMapper
                .toEntity (dtoModel, Company.class);
        Company updatedCompany = companyRepository.save (company);
        return CompletableFuture.completedFuture (companyMapper.toDTO (updatedCompany, CompanyDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "company", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (companyRepository, id);
        companyRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "company", key = "#id")
    public CompletableFuture<CompanyDTO> findById (Long id) {
        checkIfIdExist (companyRepository, id);
        Company company = companyRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Company not found with id: " + id));
        return CompletableFuture.completedFuture (companyMapper.toDTO (company, CompanyDTO.class));
    }
}
