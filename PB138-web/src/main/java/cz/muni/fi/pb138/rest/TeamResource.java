package cz.muni.fi.pb138.rest;

import cz.muni.fi.pb138.dao.TeamDao;
import cz.muni.fi.pb138.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 12.05.2017.
 */

@RestController
@RequestMapping("/team")
public class TeamResource {

    @Autowired
    private TeamDao teamDao;

    @RequestMapping(value = "/findTeam", method = RequestMethod.GET)
    public Team findTeam(@RequestParam(value = "name", required = true)String name){
        return teamDao.findTeam(name);
    }

    @RequestMapping(value = "/findAllTeams", method = RequestMethod.GET)
    public List<Team> findAllTeams(){
        return teamDao.findAllTeams();
    }


}
