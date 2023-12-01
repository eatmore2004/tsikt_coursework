/**
 * Created by Andrii Yeremenko on 11/13/23.
 */

package DAL.SQLDatabase;

import Core.Models.Result;
import DAL.SQLDatabase.Utils.ConnectionBuilder;

import java.sql.*;

public class DatabaseHandler {

    /**
     * Method that returns all rows from table using passed class name as table name
     * @warning This method is assuming that table name is plural of class name
     * @param className
     * @return Result<ResultSet> - ResultSet with all rows from table
     */
    public Result<ResultSet> GetAll(String className) {
        String query = String.format("SELECT * FROM " + className + "s");
        return Execute(query);
    }

    /**
     * Method that returns all rows from table using passed class name as table name.
     * @warning This method is assuming that table name is plural of class name
     * @param clazz
     * @param rows
     * @param id
     * @return Result<ResultSet> - ResultSet with statement execution result
     */
    public Result<ResultSet> Update(Class<?> clazz, String rows, String id) {
        String query = String.format("UPDATE %ss SET %s WHERE %s;", clazz.getSimpleName(), rows, id);
        return ExecuteVoid(query);
    }

    /**
     * Method that returns all rows from table using passed class name as table name.
     * @warning This method is assuming that table name is plural of class name
     * @param clazz
     * @param id
     * @return Result<ResultSet> - ResultSet with statement execution result
     */
    public Result<ResultSet> Delete(Class<?> clazz, String id) {
        String query = String.format("DELETE FROM %ss WHERE [id] = '%s';", clazz.getSimpleName(), id);
        return ExecuteVoid(query);
    }

    /**
     * Method that returns all rows from table using passed class name as table name.
     * @warning This method is assuming that table name is plural of class name
     * @param clazz
     * @param columns
     * @param values
     * @return Result<ResultSet> - ResultSet with statement execution result
     */
    public Result<ResultSet> Add(Class<?> clazz, String columns, String values) {
        String query = String.format("INSERT INTO %ss (%s) VALUES (%s);", clazz.getSimpleName(), columns, values);
        return ExecuteVoid(query);
    }

    /**
     * Method that executes passed query and returns ResultSet with some values fetched from database
     * @param query
     * @return Result<ResultSet> - ResultSet with some values fetched from database
     */
    private Result<ResultSet> Execute(String query) {
        DAL.SQLDatabase.Utils.ConnectionBuilder builder = new DAL.SQLDatabase.Utils.ConnectionBuilder();
        try {
            String url = builder.getUrl();
            String username = builder.getUsername();
            String password = builder.getPassword();

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return new Result<>(resultSet, true);
        } catch (SQLException e) {
            return new Result<>(e.getMessage(), false);
        }
    }

    /**
     * Method that executes passed query and returns ResultSet with statement execution result
     * @param query
     * @return Result<ResultSet> - ResultSet with statement execution result
     */
    private Result<ResultSet> ExecuteVoid(String query) {
        DAL.SQLDatabase.Utils.ConnectionBuilder builder = new ConnectionBuilder();
        try {
            Connection connection = DriverManager.getConnection(builder.getUrl(), builder.getUsername(), builder.getPassword());
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return new Result<>(true);
        } catch (SQLException e) {
            return new Result<>(e.getMessage(), false);
        }
    }
}
