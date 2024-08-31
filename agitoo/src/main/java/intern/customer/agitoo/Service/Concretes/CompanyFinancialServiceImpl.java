package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CompanyFinancialDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyFinancialMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CompanyFinancial;
import intern.customer.agitoo.Repository.Abstracts.CompanyFinancialRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyFinancialService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Cacheable(value = "customer-financial")
    public List<CompanyFinancialDTO> getAll () {
        isConnected ();
        List<CompanyFinancial> companyFinancials = companyFinancialRepository.findAll ();

        List<CompanyFinancialDTO> companyFinancialDTOS = companyFinancials
                .stream ()
                .map (companyFinancial -> companyFinancialMapper
                        .toDTO (companyFinancial, CompanyFinancialDTO.class))
                .collect (Collectors.toList ());

        return companyFinancialDTOS;
    }

    @Override
    @CachePut(value = "customer-financial", key = "")
    public CompanyFinancialDTO add (CompanyFinancialDTO dtoModel) {
        CompanyFinancial companyFinancial = companyFinancialMapper
                .toEntity (dtoModel, CompanyFinancial.class);

        CompanyFinancial savedCompanyBranchDTO = companyFinancialRepository.save (companyFinancial);
        return companyFinancialMapper.toDTO (savedCompanyBranchDTO, CompanyFinancialDTO.class);

    }

    @Override
    @CachePut(value = "customer-financial", key = "")
    public CompanyFinancialDTO update (CompanyFinancialDTO dtoModel) {
        CompanyFinancial companyFinancial = companyFinancialMapper
                .toEntity (dtoModel, CompanyFinancial.class);

        CompanyFinancial updatedCompanyFinancial = companyFinancialRepository.save (companyFinancial);
        return companyFinancialMapper.toDTO (updatedCompanyFinancial, CompanyFinancialDTO.class);
    }

    @Override
    @CacheEvict(value = "customer-financial", key = "#id")
    public void deleteById (Long id) {
        checkIfIdExist (companyFinancialRepository, id);
        companyFinancialRepository.deleteById (id);
        System.out.println (id + " " + Messages.REMOVED);
    }
}
