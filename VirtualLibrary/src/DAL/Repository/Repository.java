package DAL.Repository;

import Core.Models.BaseEntity;
import Core.Models.Result;
import DAL.DatabaseHandler;
import DAL_Abstractions.IRepository;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Repository<T extends BaseEntity> implements IRepository<T>{
    private final DatabaseHandler databaseHandler;
    private final Class<T> clazz;

    public Repository(Class<T> clazz) {
        this.clazz = clazz;
        databaseHandler = new DatabaseHandler();
    }

    public Result<List<T>> GetAll() {
        Result<ResultSet> resultSetResult = databaseHandler.GetAll(clazz.getSimpleName());
        if (!resultSetResult.getSuccess()) {
            return new Result<>(resultSetResult.getMessage(), false);
        }

        ResultSet resultSet = resultSetResult.getData();
        List<T> entities = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T entity = clazz.newInstance();
                UUID id = UUID.fromString(resultSet.getString("id"));
                Field idField = BaseEntity.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(entity, id);

                for (Field field : clazz.getDeclaredFields()) {
                    String fieldName = field.getName();
                    if (!fieldName.equals("id")) {
                        Object value = resultSet.getObject(fieldName);
                        field.setAccessible(true);
                        field.set(entity, value);
                    }
                }

                entities.add(entity);
            }

            resultSet.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
            return new Result<>(e.getMessage(), false);
        }

        return new Result<>(entities, true);
    }


    public Result<ResultSet> Update(T item) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder rows = new StringBuilder();
        StringBuilder strId = new StringBuilder("[id] = '");
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
            rows.append("[").append(fieldName).append("] = '");
            try {
                rows.append(field.get(item)).append("', ");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        rows.delete(rows.length() - 2, rows.length());

        return databaseHandler.Update(clazz, rows.toString(), strId.toString());
    }


    public Result<ResultSet> Delete(T item) {
        return databaseHandler.Delete(clazz, item.getId().toString());
    }


    public Result<ResultSet> Add(T item) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        try {
            Field id = clazz.getSuperclass().getDeclaredField("id");
            columns.append("[").append("id").append("], ");
            id.setAccessible(true);
            values.append("'").append(id.get(item)).append("', ");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return new Result<>(e.getMessage(), false);
        }

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            columns.append("[").append(fieldName).append("], ");
            try {
                values.append("'").append(field.get(item)).append("', ");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        columns.delete(columns.length() - 2, columns.length());
        values.delete(values.length() - 2, values.length());

        return databaseHandler.Add(clazz, columns.toString(), values.toString());
    }
}
