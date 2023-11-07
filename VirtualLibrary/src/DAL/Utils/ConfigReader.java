package DAL.Utils;
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

    public ConfigReader() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/DAL/server_config.xml");

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

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
