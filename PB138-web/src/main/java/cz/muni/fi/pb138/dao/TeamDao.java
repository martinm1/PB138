/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.dao;

import cz.muni.fi.pb138.entity.Team;

import java.util.List;

/**
 *
 * @author xjanco1
 */
public interface TeamDao {
    /**
     * Finds team by name
     * @param name name of the team
     * @return team with name defined in param
     */
    public Team findTeam(String name);

    /**
     * Retrieves all teams from the database
     * @return all teams from the database
     */
    public List<Team> findAllTeams();
}
