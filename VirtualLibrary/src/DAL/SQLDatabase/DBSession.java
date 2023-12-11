/**
 * Created by Andrii Yeremenko on 09/12/2023
 */

package DAL.SQLDatabase;

/**
 * DBSession class. Using to work with DatabaseHandler
 */
public class DBSession {

    private static DBHandler DBHandler;

    /**
     * Constructor. On creation creates instance of DatabaseHandler
     */
    public DBSession() {
        DBHandler = new DBHandler();
    }

    /**
     * Getter for DatabaseHandler
     * @return DatabaseHandler
     */
    public static DBHandler getDBHandler() {
        if (DBHandler == null) DBHandler = new DBHandler();
        return DBHandler;
    }
}
