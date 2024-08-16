package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Repository.Abstracts.CompanyRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public DataResult<List<Company>> getAll () {
        return new SuccessDataResult<List<Company>> (
                this.companyRepository.findAll (),
                "Companies Listed!");
    }

    @Override
    public Result Add (Company company) {
        this.companyRepository.save (company);
        return new SuccessResult (
                true,
                "Company added");
    }

    @Override
    public Result Update (Company company) {
        if (companyRepository.existsById (company.getCompanyid ())) {
            this.companyRepository.save (company);
            return new SuccessResult (true, "Company updated successfully");
        } else {
            return new ErrorResult (false, "Unfortunately company not found!");
        }
    }

    @Override
    public Result Delete (Long id) {
        if (companyRepository.existsById (id)) {
            this.companyRepository.deleteById (id);
            return new SuccessResult (true, "Company deleted successfully");
        } else {
            return new ErrorResult (false, "Company not found");
        }

    }
}
