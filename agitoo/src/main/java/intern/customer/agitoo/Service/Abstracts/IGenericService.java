package intern.customer.agitoo.Service.Abstracts;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;

import java.util.List;

public interface IGenericService<T> {

    DataResult<List<T>> getAll ();

    Result Add (T entity);

    Result Update (T entity);

    Result Delete (Long id);

}