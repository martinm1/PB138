package cz.muni.fi.pb138.dao;

import cz.muni.fi.pb138.entity.Goal;
import cz.muni.fi.pb138.entity.Match;
import cz.muni.fi.pb138.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lauro on 8.5.2017.
 */
@Repository
public class MatchDaoImpl implements MatchDao {


    @Autowired
    private Document documentDB;

    @Autowired
    private TeamDao teamDao;

    private XPath xPath;


    MatchDaoImpl(){
        XPathFactory xPathfactory = XPathFactory.newInstance();
        xPath = xPathfactory.newXPath();
    }


    private Match getMatchFromElement(Element matchElement){
        Match match = new Match();
        List<String> homePlayerList= new ArrayList<>();
        NodeList homePlayersNodeList= matchElement.getElementsByTagName("homePlayers").item(0).getChildNodes();
        for(int i=0; i<homePlayersNodeList.getLength();i++){
            homePlayerList.add(homePlayersNodeList.item(i).getTextContent());
        }
        List<String> awayPlayerList= new ArrayList<>();
        NodeList awayPlayersNodeList= matchElement.getElementsByTagName("awayPlayers").item(0).getChildNodes();
        for(int i=0; i<awayPlayersNodeList.getLength();i++){
            awayPlayerList.add(awayPlayersNodeList.item(i).getTextContent());
        }

        Element goalsElement = (Element) matchElement.getElementsByTagName("goals").item(0);
        List<Goal> homeTeamGoals = new ArrayList<>();
        Element homeTeamGoalsElement= (Element) goalsElement.getElementsByTagName("homeTeamGoals").item(0);
        NodeList homeTeamGoalsNodeList= homeTeamGoalsElement.getElementsByTagName("goal");
        for(int i=0; i<homeTeamGoalsNodeList.getLength();i++){
            Element goalElement = (Element) homeTeamGoalsNodeList.item(i);
            homeTeamGoals.add(getGoalFromElement(goalElement));
        }

        List<Goal> awayTeamGoals = new ArrayList<>();
        Element awayTeamGoalsElement= (Element) goalsElement.getElementsByTagName("awayTeamGoals").item(0);
        NodeList awayTeamGoalsNodeList= awayTeamGoalsElement.getElementsByTagName("goal");
        for(int i=0; i<awayTeamGoalsNodeList.getLength();i++){
            awayTeamGoals.add(getGoalFromElement((Element) awayTeamGoalsNodeList.item(i)));
        }
        Team homeTeam=teamDao.findTeam(matchElement.getElementsByTagName("homeTeam").item(0).getTextContent());
        Team awayTeam=teamDao.findTeam(matchElement.getElementsByTagName("awayTeam").item(0).getTextContent());
        int homeTeamShots= Integer.parseInt(matchElement.getElementsByTagName("homeTeamShots").item(0).getTextContent());
        int awayTeamShots= Integer.parseInt(matchElement.getElementsByTagName("awayTeamShots").item(0).getTextContent());
        int spectators= Integer.parseInt(matchElement.getElementsByTagName("spectators").item(0).getTextContent());

        match.setAwayPlayerList(awayPlayerList);
        match.setHomePlayerList(homePlayerList);
        match.setAwayTeam(awayTeam);
        match.setHomeTeam(homeTeam);
        match.setAwayTeamGoals(awayTeamGoals);
        match.setHomeTeamGoals(homeTeamGoals);
        match.setHomeTeamShots(homeTeamShots);
        match.setAwayTeamShots(awayTeamShots);
        match.setSpectators(spectators);

        return match;
    }

    private Goal getGoalFromElement(Element goalElement){
        Goal goal = new Goal();
        goal.setMinute(Integer.parseInt(goalElement.getElementsByTagName("minute").item(0).getTextContent()));
        goal.setScorer(goalElement.getElementsByTagName("scorer").item(0).getTextContent());
        return goal;
    }


    @Override
    public List<Match> getAllMatches() {
        NodeList matchesNodeList = documentDB.getElementsByTagName("match");
        List<Match> allMatches = new ArrayList<>();
        for(int i=0;i<matchesNodeList.getLength();i++){
            Element matchElement = (Element) matchesNodeList.item(i);
            allMatches.add(getMatchFromElement(matchElement));
        }
        return allMatches;
    }

    @Override
    public List<Match> getAllMatchesAgainstTeam(Team team) {
        List<Match> matches = new ArrayList<>();
        try{
            XPathExpression homeExpr = xPath.compile("./database/matches/match/homeTeam[text()='"+team.getName()+"']/parent::match");
            NodeList homeNodeList= (NodeList) homeExpr.evaluate(documentDB, XPathConstants.NODESET);
            for(int i=0;i<homeNodeList.getLength();i++){
                matches.add(getMatchFromElement((Element) homeNodeList.item(i)));
            }
            XPathExpression awayExpr = xPath.compile("./database/matches/match/awayTeam[text()='"+team.getName()+"']/parent::match");
            NodeList awayNodeList= (NodeList) awayExpr.evaluate(documentDB, XPathConstants.NODESET);
            for(int i=0;i<awayNodeList.getLength();i++){
                matches.add(getMatchFromElement((Element) awayNodeList.item(i)));
            }
        } catch (XPathExpressionException e) {

        }

        return matches;
    }

    @Override
    public List<Match> getAllHomeMatches() {
        List<Match> matches = new ArrayList<>();
        try{
            XPathExpression expr = xPath.compile("./database/matches/match/homeTeam[text()='HC Sparta Praha']/parent::match");
            NodeList homeMatchesNodeList= (NodeList) expr.evaluate(documentDB, XPathConstants.NODESET);
            for(int i=0;i<homeMatchesNodeList.getLength();i++){
                matches.add(getMatchFromElement((Element) homeMatchesNodeList.item(i)));
            }
        } catch (XPathExpressionException e) {

        }
        return matches;
    }

    @Override
    public List<Match> getAllAwayMatches() {
        List<Match> matches = new ArrayList<>();
        try{
            XPathExpression expr = xPath.compile("./database/matches/match/awayTeam[text()='HC Sparta Praha']/parent::match");
            NodeList awayMatchesNodeList= (NodeList) expr.evaluate(documentDB, XPathConstants.NODESET);
            for(int i=0;i<awayMatchesNodeList.getLength();i++){
                matches.add(getMatchFromElement((Element) awayMatchesNodeList.item(i)));
            }
        } catch (XPathExpressionException e) {

        }
        return matches;
    }

    @Override
    public int countTotalSpectators() {
        int spectators=0;
        try{
            XPathExpression expr = xPath.compile("./database/matches/match/spectators/text()");
            NodeList spectatorsList= (NodeList) expr.evaluate(documentDB, XPathConstants.NODESET);
            for(int i=0;i<spectatorsList.getLength();i++){
                spectators+=Integer.parseInt(spectatorsList.item(i).getTextContent());
            }
        } catch (XPathExpressionException e) {
        }
        return spectators;
    }

    @Override
    public int countTotalShots() {
        int totalShots=0;
        try{
            XPathExpression homeExpr = xPath.compile("./database/matches/match/awayTeam[text()='HC Sparta Praha']/parent::match/awayTeamShots/text()");
            NodeList homeShotsList= (NodeList) homeExpr.evaluate(documentDB, XPathConstants.NODESET);
            for(int i=0;i<homeShotsList.getLength();i++){
                totalShots+=Integer.parseInt(homeShotsList.item(i).getTextContent());
            }
            XPathExpression awayExpr = xPath.compile("./database/matches/match/homeTeam[text()='HC Sparta Praha']/parent::match/homeTeamShots/text()");
            NodeList awayShotsList= (NodeList) homeExpr.evaluate(documentDB, XPathConstants.NODESET);
            for(int i=0;i<awayShotsList.getLength();i++){
                totalShots+=Integer.parseInt(awayShotsList.item(i).getTextContent());
            }
        } catch (XPathExpressionException e) {
        }
        return totalShots;
    }
}
