/**
 * Created by Andrii Yeremenko on 11/7/23.
 */

package DAL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Result;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 */
public interface IRepository<T extends BaseEntity> {

    /**
     * Method for getting all items from repository
     * @return Result<List<T>> - list of items BaseEntity
     */
    Result<List<T>> GetAll();

    /**
     * Method for updating existing item in repository
     * @param item
     * @return Result<ResultSet> - result of operation
     */
    Result<ResultSet> Update(T item);

    /**
     * Method for deleting existing item from repository
     * @param item
     * @return Result<ResultSet> - result of operation
     */
    Result<ResultSet> Delete(T item);

    /**
     * Method for adding new item to repository
     * @param item
     * @return Result<ResultSet> - result of operation
     */
    Result<ResultSet> Add(T item);
}
