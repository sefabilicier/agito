package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyBranchMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CompanyBranch;
import intern.customer.agitoo.Repository.Abstracts.CompanyBranchRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyBranchService;
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
public class CompanyBranchServiceImpl implements ICompanyBranchService {

    @Autowired
    private CompanyBranchRepository companyBranchRepository;

    @Autowired
    private CompanyBranchMapper companyBranchMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "company-branch")
    public CompletableFuture<List<CompanyBranchDTO>> getAll () {
        isConnected (); //veritabanına bağlandı mı?
        List<CompanyBranch> companyBranches = companyBranchRepository.findAll (); //repodaki tüm verileri al

        List<CompanyBranchDTO> companyBranchDTOS = companyBranches //dto classına listele
                .stream () //repodaki tüm verileri dolaş
                .map (companyBranch -> companyBranchMapper //ve bu dolaştığın tüm verileri dto classına maple
                        .toDTO (companyBranch, CompanyBranchDTO.class))
                .collect (Collectors.toList ()); //ve hepsini liste olarak döndür
        return CompletableFuture.completedFuture (companyBranchDTOS); //getAll çağrılınca bu listeye eklediğin dtodaki verilerini listele
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "company-branch", key = "#result.branchID")
    public CompletableFuture<CompanyBranchDTO> add (CompanyBranchDTO dtoModel) {

        CompanyBranch companyBranch = companyBranchMapper
                .toEntity (dtoModel, CompanyBranch.class);

        CompanyBranch savedCompanyBranchDTO = companyBranchRepository.save (companyBranch);

        return CompletableFuture.completedFuture (companyBranchMapper.toDTO (savedCompanyBranchDTO, CompanyBranchDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "company-branch", key = "#result.branchID")
    public CompletableFuture<CompanyBranchDTO> update (CompanyBranchDTO dtoModel) {

        //existsByName (dtoModel.getBranchName ());

        CompanyBranch companyBranch = companyBranchMapper
                .toEntity (dtoModel, CompanyBranch.class);

        CompanyBranch updatedCompanyBranchDTO = companyBranchRepository.save (companyBranch);
        return CompletableFuture.completedFuture (companyBranchMapper.toDTO (updatedCompanyBranchDTO, CompanyBranchDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "company-branch", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (companyBranchRepository, id);
        this.companyBranchRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "company-branch", key = "#id")
    public CompletableFuture<CompanyBranchDTO> findById (Long id) {
        checkIfIdExist (companyBranchRepository, id);
        CompanyBranch companyBranch = companyBranchRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Branch not found with id: " + id));
        return CompletableFuture.completedFuture (companyBranchMapper.toDTO (companyBranch, CompanyBranchDTO.class));
    }


    //CHECKING METHODS;

    @Override
    public String existsByName (String name) {
        return name;
    }
}
