package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CompanyFinancial;
import intern.customer.agitoo.Repository.Abstracts.CompanyFinancialRepository;
import intern.customer.agitoo.Service.Abstracts.ICompanyFinancialService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFinancialServiceImpl implements ICompanyFinancialService {

    @Autowired
    private CompanyFinancialRepository companyFinancialRepository;


    @Override
    public DataResult<List<CompanyFinancial>> getAll () {
        return new SuccessDataResult<List<CompanyFinancial>> (
                this.companyFinancialRepository.findAll (),
                "Company financials listed!"
        );
    }

    @Override
    public Result Add (CompanyFinancial entity) {
        this.companyFinancialRepository.save (entity);
        return new SuccessResult (true, "Company financial added!");
    }

    @Override
    public Result Update (CompanyFinancial entity) {
        if (companyFinancialRepository.existsById (entity.getFinancialID ())) {
            this.companyFinancialRepository.save (entity);
            return new SuccessResult (true, "Company financial updated!");
        } else {
            return new ErrorResult (false, "Unfortunately company branch not found!");
        }
    }

    @Override
    public Result Delete (Long id) {
        if (companyFinancialRepository.existsById (id)) {
            this.companyFinancialRepository.deleteById (id);
            return new SuccessResult (true, "Company financial successfully");
        } else {
            return new ErrorResult (false, "Company branch not found!");
        }
    }
}
