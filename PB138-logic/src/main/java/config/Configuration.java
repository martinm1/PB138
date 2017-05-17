package config;


import dao.TeamDaoImpl;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xjanco1
 */
@org.springframework.context.annotation.Configuration
@ComponentScan(basePackageClasses = TeamDaoImpl.class)
public class Configuration {
    
    @Bean
    public Document dbFileDocument() throws SAXException, IOException, ParserConfigurationException{
        String schema = getClass().getClassLoader().getResource("db_xml_schema.xsd").getPath();
        String database = getClass().getClassLoader().getResource("db.xml").getPath();

        System.out.println("im tryin to validate database");
        String validated = validateDatabase(database, schema);
        if(validated != null && validated.equals(database + " is valid")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            String path = getClass().getClassLoader().getResource("db.xml").getPath();
            return builder.parse(path);
        }
        System.err.println("xml database cannot be loaded, because the format of database is" +
                " wrong !!!!");
        return null;
    }

    /**
     * Function validates xml database with xml schema
     */
    public String validateDatabase(String database, String schema) {
        SchemaValidator validator = new SchemaValidator(schema);
        String result = null;
        try {
            result = validator.validate(database);
        } catch (IOException ex) {
            System.err.println("File not found: "+ex.getMessage());
        }

        return result;
    }
}
