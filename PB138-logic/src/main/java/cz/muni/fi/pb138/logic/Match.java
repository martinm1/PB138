/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.logic;

import java.util.List;

/**
 *
 * @author martin
 */
public class Match {
  private Long id;
  private Team team1;
  private Team team2;
  private List<Player> team1PlayerList;
  private List<Player> team2PlayerList;
  private List<Action> actionList;
  private String commentary;
}
