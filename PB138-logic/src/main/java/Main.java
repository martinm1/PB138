
import config.Configuration;
import dao.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xjanco1
 */
public class Main {
    
    public static void main(String[] args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
        TeamDao teamdao= applicationContext.getBean(TeamDao.class);
        teamdao.findAllTeams();
    }
}
