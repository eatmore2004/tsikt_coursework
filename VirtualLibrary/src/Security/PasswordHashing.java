package Security;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHashing {
    private static final String XML_FILE_PATH = "src/Security/security.xml";

    private static byte[] getSaltFromXML() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(XML_FILE_PATH));
        Element root = document.getDocumentElement();
        String saltString = root.getElementsByTagName("salt").item(0).getTextContent();
        return Base64.getDecoder().decode(saltString);
    }

    public static String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(getSaltFromXML());
        byte[] hashedPassword = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) throws Exception {
        String inputHashedPassword = hashPassword(plainPassword);
        return inputHashedPassword.equals(hashedPassword);
    }
}
