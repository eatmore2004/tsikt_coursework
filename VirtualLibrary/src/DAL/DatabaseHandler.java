package DAL;

import Core.Models.BaseEntity;
import Core.Models.Result;
import DAL.ConnectionBuilder;

import java.sql.*;

public class DatabaseHandler {

    public Result<ResultSet> GetAll(String className) {
        String query = String.format("SELECT * FROM " + className + "s");
        return Execute(query);
    }

    public Result<ResultSet> Update(Class<?> clazz, String rows, String id) {
        String query = String.format("UPDATE %ss SET %s WHERE %s;", clazz.getSimpleName(), rows, id);
        return Execute(query);
    }

    public Result<ResultSet> Delete(Class<?> clazz, String id) {
        String query = String.format("DELETE FROM %ss WHERE [id] = '%s';", clazz.getSimpleName(), id);
        return Execute(query);
    }

    public Result<ResultSet> Add(Class<?> clazz, String columns, String values) {
        String query = String.format("INSERT INTO %ss (%s) VALUES (%s);", clazz.getSimpleName(), columns, values);
        return Execute(query);
    }

    private Result<ResultSet> Execute(String query) {
        DAL.ConnectionBuilder builder = new ConnectionBuilder();
        try {
            Connection connection = DriverManager.getConnection(builder.getUrl(), builder.getUsername(), builder.getPassword());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return new Result<>(resultSet, true);
        } catch (SQLException e) {
            return new Result<>(e.getMessage(), false);
        }
    }
}
