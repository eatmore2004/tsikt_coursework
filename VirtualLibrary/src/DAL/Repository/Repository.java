/**
 * Created by Andrii Yeremenko on 11/7/23.
 */

package DAL.Repository;

import Core.Models.BaseEntity;
import Core.Models.Result;
import DAL.SQLDatabase.DBHandler;
import DAL.SQLDatabase.DBSession;
import DAL_Abstractions.IRepository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Repository class. Implements IRepository interface
 * @see IRepository
 */
public class Repository<T extends BaseEntity> implements IRepository<T>{
    private final DBHandler DBHandler;
    private final Class<T> clazz;

    /**
     * Constructor for Repository class
     * @param clazz - class of entity
     * @warning - clazz must be inherited from BaseEntity
     */
    public Repository(Class<T> clazz) {
        this.clazz = clazz;
        DBHandler = DBSession.getDBHandler();
    }

    public Result<List<T>> GetAll() {
        Result<ResultSet> resultSetResult = DBHandler.GetAll(clazz.getSimpleName());
        if (!resultSetResult.getSuccess()) {
            return new Result<>(resultSetResult.getMessage(), false);
        }

        ResultSet resultSet = resultSetResult.getData();
        List<T> entities = new ArrayList<>();
        if (resultSet == null) return new Result<>("No data found",false);
        try {
            while (resultSet.next()) {
                T entity = clazz.getDeclaredConstructor().newInstance();
                UUID id = UUID.fromString(resultSet.getString("id"));
                Field idField = BaseEntity.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(entity, id);

                for (Field field : clazz.getDeclaredFields()) {
                    String fieldName = field.getName();
                    if (fieldName.equals("rentedBy")) {
                        Object value = resultSet.getObject(fieldName);
                        field.setAccessible(true);
                        if (!value.equals("null")) {
                            field.set(entity, UUID.fromString(value.toString()));
                        }else {
                            field.set(entity, null);
                        }
                    }else if (!fieldName.equals("id")) {
                        Object value = resultSet.getObject(fieldName);
                        field.setAccessible(true);
                        field.set(entity, value);
                    }
                }

                entities.add(entity);
            }

            resultSet.close();
        } catch (SQLException | InvocationTargetException | IllegalAccessException |
                 InstantiationException | NoSuchFieldException |
                 NoSuchMethodException e) {
            return new Result<>(e.getMessage(), false);
        }

        return new Result<>(entities, true);
    }


    public Result<ResultSet> Update(T item) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder rows = new StringBuilder();
        StringBuilder strId = new StringBuilder("id = '");
        try {
            Field id = clazz.getSuperclass().getDeclaredField("id");
            id.setAccessible(true);
            strId.append(id.get(item)).append("'");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return new Result<>(e.getMessage(), false);
        }

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            rows.append(fieldName).append(" = '");
            try {
                rows.append(field.get(item)).append("', ");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        rows.delete(rows.length() - 2, rows.length());

        return DBHandler.Update(clazz, rows.toString(), strId.toString());
    }


    public Result<ResultSet> Delete(T item) {
        return DBHandler.Delete(clazz, item.getId().toString());
    }


    public Result<ResultSet> Add(T item) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        try {
            Field id = clazz.getSuperclass().getDeclaredField("id");
            columns.append("id").append(", ");
            id.setAccessible(true);
            values.append("'").append(id.get(item)).append("', ");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return new Result<>(e.getMessage(), false);
        }

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            columns.append(fieldName).append(", ");
            try {
                values.append("'").append(field.get(item)).append("', ");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        columns.delete(columns.length() - 2, columns.length());
        values.delete(values.length() - 2, values.length());

        return DBHandler.Add(clazz, columns.toString(), values.toString());
    }
}
