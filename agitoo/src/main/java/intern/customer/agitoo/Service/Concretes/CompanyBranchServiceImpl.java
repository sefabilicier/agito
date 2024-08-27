package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyBranchMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CompanyBranch;
import intern.customer.agitoo.Repository.Abstracts.CompanyBranchRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyBranchService;
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
public class CompanyBranchServiceImpl implements ICompanyBranchService {

    @Autowired
    private CompanyBranchRepository companyBranchRepository;

    @Autowired
    private CompanyBranchMapper companyBranchMapper;

    @Override
    @Cacheable(value = "customer-branch")
    public List<CompanyBranchDTO> getAll () {
        toDatabase.isConnected (); //veritabanına bağlandı mı?
        List<CompanyBranch> companyBranches = companyBranchRepository.findAll (); //repodaki tüm verileri al

        List<CompanyBranchDTO> companyBranchDTOS = companyBranches //dto classına listele
                .stream () //repodaki tüm verileri dolaş
                .map (companyBranch -> companyBranchMapper //ve bu dolaştığın tüm verileri dto classına maple
                                .toDTO (companyBranch, CompanyBranchDTO.class))
                                        .collect(Collectors.toList ()); //ve hepsini liste olarak döndür
        return companyBranchDTOS; //getAll çağrılınca bu listeye eklediğin dtodaki verilerini listele
    }

    @Override
    @CachePut(value = "customer-branch", key = "")
    public CompanyBranchDTO add (CompanyBranchDTO dtoModel) {

        existsByName (dtoModel.getBranchName ());

         return getCompanyBranchDTO (dtoModel);
    }

    private CompanyBranchDTO getCompanyBranchDTO (CompanyBranchDTO dtoModel) {
        CompanyBranch companyBranch = companyBranchMapper
                .toEntity (dtoModel, CompanyBranch.class);

        CompanyBranch savedCompanyBranchDTO = companyBranchRepository.save (companyBranch);

        return companyBranchMapper.toDTO (savedCompanyBranchDTO, CompanyBranchDTO.class);
    }

    @Override
    @CachePut(value = "customer-branch", key = "")
    public CompanyBranchDTO update (CompanyBranchDTO dtoModel) {

        existsByName (dtoModel.getBranchName ());

        CompanyBranch companyBranch = companyBranchMapper
                .toEntity (dtoModel, CompanyBranch.class);

        CompanyBranch updatedCompanyBranchDTO = companyBranchRepository.save (companyBranch);
        return companyBranchMapper.toDTO (updatedCompanyBranchDTO, CompanyBranchDTO.class);
    }

    @Override
    @CacheEvict(value = "customer-branch", key = "#id")
    public void deleteById (Long id) {
        CommonBusinessRules.checkIfIdExist (companyBranchRepository, id);
        this.companyBranchRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }



    //CHECKING METHODS;

    @Override
    public String existsByName (String name) {
        return "";
    }
}
