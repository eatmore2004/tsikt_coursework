package DAL;

import DAL.Utils.ConfigReader;

public class ConnectionBuilder {
    ConfigReader configReader;

    public ConnectionBuilder(){
        configReader = new ConfigReader();
    }
    public String getUrl(){
        return String.format("jdbc:sqlserver://%s:%d;databaseName=%s;encrypt=true;trustServerCertificate=true",
                configReader.getHost(),
                configReader.getPort(),
                configReader.getDatabaseName());
    }

    public String getUsername(){
        return configReader.getUsername();
    }

    public String getPassword(){
        return configReader.getPassword();
    }
}
