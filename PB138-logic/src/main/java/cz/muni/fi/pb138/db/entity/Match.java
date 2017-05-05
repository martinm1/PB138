/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.db.entity;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author martin
 */
public class Match {
  private Long id;
  private LocalDate matchDate;
  private Team homeTeam;
  private Team awayTeam;
  private List<String> homePlayerList;
  private List<String> awayPlayerList;
  private List<Goal> goals;
  private int spectators;
  private int homeTeamShots;
  private int awayTeamShots;
  private String description = null;
}
