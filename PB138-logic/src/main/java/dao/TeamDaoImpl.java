/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import cz.muni.fi.pb138.db.entity.Team;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Team> findAllTeams() {
        System.out.println(documentDB.getElementsByTagName("team").getLength());
        return null;
    }
    
    
  
}
