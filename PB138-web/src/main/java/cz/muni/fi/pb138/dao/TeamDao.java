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
    public Team findTeam(String name);
    public List<Team> findAllTeams();
}
