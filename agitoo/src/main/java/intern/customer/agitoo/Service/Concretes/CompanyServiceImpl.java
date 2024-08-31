package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Repository.Abstracts.CompanyRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyService;
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
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    @Cacheable(value = "company")
    public List<CompanyDTO> getAll () {
        isConnected ();
        List<Company> companies = companyRepository.findAll ();
        List<CompanyDTO> companyDTOS = companies
                .stream ()
                .map (company -> companyMapper.toDTO (company, CompanyDTO.class))
                .collect (Collectors.toList ());

        return companyDTOS;
    }

    @Override
    @CachePut(value = "company", key = "")
    public CompanyDTO add (CompanyDTO dtoModel) {
        Company company = companyMapper
                .toEntity (dtoModel, Company.class);
        Company savedCompany = companyRepository.save (company);
        return companyMapper.toDTO (savedCompany, CompanyDTO.class);
    }

    @Override
    @CachePut(value = "company", key = "")
    public CompanyDTO update (CompanyDTO dtoModel) {
        Company company = companyMapper
                .toEntity (dtoModel, Company.class);
        Company updatedCompany = companyRepository.save (company);
        return companyMapper.toDTO (updatedCompany, CompanyDTO.class);
    }

    @Override
    @CacheEvict(value = "company", key = "#id")
    public void deleteById (Long id) {
        checkIfIdExist (companyRepository, id);
        companyRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
