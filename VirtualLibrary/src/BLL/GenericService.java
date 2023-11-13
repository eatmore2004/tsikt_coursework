package BLL;

import BLL_Abstractions.IGenericService;
import Core.Models.BaseEntity;
import Core.Models.Result;
import DAL_Abstractions.IRepository;

import java.util.List;

/**
 * GenericService class
 * created by Andrii Yeremenko
 * @see IGenericService
 */
public class GenericService implements IGenericService {

    private final IRepository repository;

    public GenericService(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<BaseEntity>> GetAll() {
        return repository.GetAll();
    }

    @Override
    public Result<String> Edit(BaseEntity item) {
        return repository.Update(item);
    }

    @Override
    public Result<String> Delete(BaseEntity item) {
        return repository.Delete(item);
    }

    @Override
    public Result<String> Add(BaseEntity item) {
        return repository.Add(item);
    }
}
