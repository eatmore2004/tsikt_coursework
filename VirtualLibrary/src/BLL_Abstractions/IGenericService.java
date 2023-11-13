package BLL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Result;

import java.util.List;

/**
 * IGenericService interface
 * created by Andrii Yeremenko
 * @param <T extends BaseEntity> - generic type of BaseEntity
 */
public interface IGenericService<T extends BaseEntity> {

    /**
     * Method to get all items
     * @return Result<List<T>> - list of items
     */
    Result<List<T>> GetAll();

    /**
     * Method to edit item, by passing item
     * @return Result<T> - item
     */
    Result<String> Edit(T item);

    /**
     * Method to delete item, by passing item
     * @param item
     * @return
     */
    Result<String> Delete(T item);

    /**
     * Method to add item, by passing item
     * @param item
     * @return
     */
    Result<String> Add(T item);
}