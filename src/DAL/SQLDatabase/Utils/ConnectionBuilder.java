/**
 * Created by Andrii Yeremenko on 11/7/23.
 */

package DAL.SQLDatabase.Utils;

public class ConnectionBuilder {
    ConfigReader configReader;


    /**
     * Constructor. On creation creates instance of ConfigReader
     */
    public ConnectionBuilder(){
        configReader = new ConfigReader();
    }

    /**
     * Getter for url
     * @return String url
     */
    public String getUrl(){
        return String.format("jdbc:sqlserver://%s:%d;databaseName=%s;encrypt=true;trustServerCertificate=true",
                configReader.getHost(),
                configReader.getPort(),
                configReader.getDatabaseName());
    }

    /**
     * Getter for username
     * @return String username
     */
    public String getUsername(){
        return configReader.getUsername();
    }

    /**
     * Getter for password
     * @return String password
     */
    public String getPassword(){
        return configReader.getPassword();
    }
}
