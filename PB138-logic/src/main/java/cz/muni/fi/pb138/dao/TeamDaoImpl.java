/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.dao;

import cz.muni.fi.pb138.entity.Team;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;

/**
 *
 * @author xjanco1
 */
@Repository
public class TeamDaoImpl implements TeamDao{

    @Autowired
    private Document documentDB;
    
    @Override
    public Team findTeam(String name) {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        Team team = null;
        try {
            XPathExpression expr = xpath.compile("./database/teams/team/name[text()='"+name+"']/parent::team");
            NodeList nodeList= (NodeList) expr.evaluate(documentDB, XPathConstants.NODESET);
            Element element= (Element) nodeList.item(0);
            team= new Team();
            team.setName(element.getElementsByTagName("name").item(0).getTextContent());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public List<Team> findAllTeams() {
        NodeList nodes =documentDB.getElementsByTagName("team");
        List<Team> teamList = new ArrayList<>();
        for(int i=0;i<nodes.getLength();i++){
            Element teamElement = (Element) nodes.item(i);
            Element nameElement= (Element) teamElement.getElementsByTagName("name").item(0);
            Element pathElement= (Element) teamElement.getElementsByTagName("logo").item(0);
            Team team = new Team();
            team.setName(nameElement.getTextContent());
            team.setLogoPath(pathElement.getTextContent());
            teamList.add(team);
        }
        return teamList;
    }
    
    
  
}
