/**
 * Created by Andrii Yeremenko on 11/7/23.
 */

package DAL.SQLDatabase;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigReader {
    private String host;
    private int port;
    private String databaseName;
    private String username;
    private String password;

    /**
     * Constructor. On creation reads config file and sets values
     */
    public ConfigReader() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/DAL/SQLDatabase/server_config.xml");

            Element config = document.getDocumentElement();
            host = config.getElementsByTagName("host").item(0).getTextContent();
            port = Integer.parseInt(config.getElementsByTagName("port").item(0).getTextContent());
            databaseName = config.getElementsByTagName("databaseName").item(0).getTextContent();
            username = config.getElementsByTagName("username").item(0).getTextContent();
            password = config.getElementsByTagName("password").item(0).getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for host
     * @return String host
     */
    public String getHost() {
        return host;
    }

    /**
     * Getter for port
     * @return int port
     */
    public int getPort() {
        return port;
    }

    /**
     * Getter for databaseName
     * @return String databaseName
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Getter for username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for password
     * @return String password
     */
    public String getPassword() {
        return password;
    }
}
