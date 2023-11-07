package DAL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Result;

import java.sql.ResultSet;
import java.util.List;

public interface IRepository<T extends BaseEntity> {
    Result<List<T>> GetAll();
    Result<ResultSet> Update(T item);
    Result<ResultSet> Delete(T item);
    Result<ResultSet> Add(T item);
}
