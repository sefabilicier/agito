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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFinancialServiceImpl implements ICompanyFinancialService {

    @Autowired
    private CompanyFinancialRepository companyFinancialRepository;

    @Autowired
    private CompanyFinancialMapper companyFinancialMapper;

    @Override
    public List<CompanyFinancialDTO> getAll () {
        List<CompanyFinancial> companyFinancials = companyFinancialRepository.findAll ();

        List<CompanyFinancialDTO> companyFinancialDTOS = companyFinancials
                .stream ()
                .map (companyFinancial -> companyFinancialMapper
                        .toDTO (companyFinancial, CompanyFinancialDTO.class))
                                .collect(Collectors.toList ());

        return companyFinancialDTOS;
    }

    @Override
    public CompanyFinancialDTO add (CompanyFinancialDTO dtoModel) {
        CompanyFinancial companyFinancial = companyFinancialMapper
                .toEntity (dtoModel, CompanyFinancial.class);

        CompanyFinancial savedCompanyBranchDTO = companyFinancialRepository.save (companyFinancial);
        return companyFinancialMapper.toDTO (savedCompanyBranchDTO, CompanyFinancialDTO.class);

    }

    @Override
    public CompanyFinancialDTO update (CompanyFinancialDTO dtoModel) {
        CompanyFinancial companyFinancial = companyFinancialMapper
                .toEntity (dtoModel, CompanyFinancial.class);

        CompanyFinancial updatedCompanyFinancial = companyFinancialRepository.save (companyFinancial);
        return companyFinancialMapper.toDTO (updatedCompanyFinancial, CompanyFinancialDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        companyFinancialRepository.deleteById (id);
        System.out.println (id + " " + Messages.REMOVED);
    }
}
