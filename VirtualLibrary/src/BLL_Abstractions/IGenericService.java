package BLL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Result;

import java.util.List;
import java.util.function.Predicate;

public interface IGenericService<T extends BaseEntity> {
    Result<T> GetAll();
    Result<String> Edit(T item);
    Result<String> Delete(T item);
    Result<String> Add(T item);
}