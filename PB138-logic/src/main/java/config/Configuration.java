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
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        String path= getClass().getClassLoader().getResource("db.xml").getPath();
       return builder.parse(path);
    }
}
