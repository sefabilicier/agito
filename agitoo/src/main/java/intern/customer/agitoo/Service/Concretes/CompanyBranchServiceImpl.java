package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CompanyBranch;
import intern.customer.agitoo.Repository.Abstracts.CompanyBranchRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyBranchService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CompanyBranchServiceImpl implements ICompanyBranchService {

    @Autowired
    private CompanyBranchRepository companyBranchRepository;


    @Override
    public DataResult<List<CompanyBranch>> getAll () {
        return new SuccessDataResult<List<CompanyBranch>> (
                this.companyBranchRepository.findAll (),
                "Company branches listed!");
    }

    @Override
    public Result Add (CompanyBranch entity) {
        this.companyBranchRepository.save (entity);
        return new SuccessResult (true, "Company Branch added!");
    }

    @Override
    public Result Update (CompanyBranch entity) {
        if (companyBranchRepository.existsById (entity.getBranchID ())) {
            this.companyBranchRepository.save (entity);
            return new SuccessResult (true, "Company branch updated!");
        } else {
            return new ErrorResult (false, "Unfortunately company branch not found!");
        }
    }

    @Override
    public Result Delete (Long id) {
        if (companyBranchRepository.existsById (id)) {
            this.companyBranchRepository.deleteById (id);
            return new SuccessResult (true, "Company deleted successfully");
        } else {
            return new ErrorResult (false, "Company branch not found!");
        }
    }
}
