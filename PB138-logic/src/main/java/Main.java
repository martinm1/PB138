
import cz.muni.fi.pb138.config.Configuration;
import cz.muni.fi.pb138.entity.Match;
import cz.muni.fi.pb138.dao.MatchDao;
import cz.muni.fi.pb138.dao.TeamDao;
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
        MatchDao matchDao = applicationContext.getBean(MatchDao.class);

        System.out.println("All matches");
        for (Match match: matchDao.getAllMatches()){
            System.out.println(match.getHomeTeam().getName()+": "+match.getAwayTeam().getName());
        }
        System.out.println("---------------");
        System.out.println("All matches against HC VÍTKOVICE RIDERA");
        for(Match match: matchDao.getAllMatchesAgainstTeam(teamdao.findTeam("HC VÍTKOVICE RIDERA"))){
            System.out.println(match.getHomeTeam().getName()+": "+match.getAwayTeam().getName());
        }
        System.out.println("---------------");
        System.out.println("Total shots: "+matchDao.countTotalShots());
        System.out.println("Total spectators: "+matchDao.countTotalSpectators());
        System.out.println("---------------");
        System.out.println("All home matches");
        for(Match match: matchDao.getAllHomeMatches()){
            System.out.println(match.getHomeTeam().getName()+": "+match.getAwayTeam().getName());
        }
        System.out.println("All away matches");
        for(Match match: matchDao.getAllAwayMatches()){
            System.out.println(match.getHomeTeam().getName()+": "+match.getAwayTeam().getName());
        }



    }
}
