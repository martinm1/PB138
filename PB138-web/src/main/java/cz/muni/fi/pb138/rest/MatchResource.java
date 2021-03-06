package cz.muni.fi.pb138.rest;

import cz.muni.fi.pb138.dao.MatchDao;
import cz.muni.fi.pb138.dao.TeamDao;
import cz.muni.fi.pb138.entity.Match;
import cz.muni.fi.pb138.entity.Team;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 12.05.2017.
 */

@RestController
@RequestMapping("/match")
public class MatchResource {
    @Inject
    private MatchDao matchDao;

    @Inject
    private TeamDao teamDao;

    @RequestMapping(value = "/getAllMatches", method = RequestMethod.GET)
    public List<Match> getAllMatches(){
        return matchDao.getAllMatches();
    }

    @RequestMapping(value = "/getAllMatchesAgainstTeam", method = RequestMethod.GET)
    public List<Match> getAllMatchesAgainstTeam(@RequestParam(value = "name", required = true)String name){
        if(name.equals(""))
            return matchDao.getAllMatches();
        Team team = teamDao.findTeam(name);
        return matchDao.getAllMatchesAgainstTeam(team);
    }

    @RequestMapping(value = "/getAllHomeMatches", method = RequestMethod.GET)
    public List<Match> getAllHomeMatches(){
        return matchDao.getAllHomeMatches();
    }

    @RequestMapping(value = "/getAllAwayMatches", method = RequestMethod.GET)
    public List<Match> getAllAwayMatches(){
        return matchDao.getAllAwayMatches();
    }

    @RequestMapping(value = "/countTotalSpectators", method = RequestMethod.GET)
    public int countTotalSpectators(){
        return matchDao.countTotalSpectators();
    }

    @RequestMapping(value = "/countTotalShots", method = RequestMethod.GET)
    public int countTotalShots(){
        return matchDao.countTotalShots();
    }

    @RequestMapping(value = "/findMatchById", method = RequestMethod.GET)
    public Match findMatchById(@RequestParam(value = "id", required = true)Long id){
        return matchDao.findMatchById(id);
    }
}
