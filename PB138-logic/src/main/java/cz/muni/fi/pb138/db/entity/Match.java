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
  private List<Goal> hometeamGoals;
  private List<Goal> awayTeamGoals;
  private int spectators;
  private int homeTeamShots;
  private int awayTeamShots;
  private String description = null;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getMatchDate() {
    return matchDate;
  }

  public void setMatchDate(LocalDate matchDate) {
    this.matchDate = matchDate;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(Team homeTeam) {
    this.homeTeam = homeTeam;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(Team awayTeam) {
    this.awayTeam = awayTeam;
  }

  public List<String> getHomePlayerList() {
    return homePlayerList;
  }

  public void setHomePlayerList(List<String> homePlayerList) {
    this.homePlayerList = homePlayerList;
  }

  public List<String> getAwayPlayerList() {
    return awayPlayerList;
  }

  public void setAwayPlayerList(List<String> awayPlayerList) {
    this.awayPlayerList = awayPlayerList;
  }

  public List<Goal> getHometeamGoals() {
    return hometeamGoals;
  }

  public void setHometeamGoals(List<Goal> hometeamGoals) {
    this.hometeamGoals = hometeamGoals;
  }

  public List<Goal> getAwayTeamGoals() {
    return awayTeamGoals;
  }

  public void setAwayTeamGoals(List<Goal> awayTeamGoals) {
    this.awayTeamGoals = awayTeamGoals;
  }

  public int getSpectators() {
    return spectators;
  }

  public void setSpectators(int spectators) {
    this.spectators = spectators;
  }

  public int getHomeTeamShots() {
    return homeTeamShots;
  }

  public void setHomeTeamShots(int homeTeamShots) {
    this.homeTeamShots = homeTeamShots;
  }

  public int getAwayTeamShots() {
    return awayTeamShots;
  }

  public void setAwayTeamShots(int awayTeamShots) {
    this.awayTeamShots = awayTeamShots;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Match match = (Match) o;

    return getMatchDate() != null ? getMatchDate().equals(match.getMatchDate()) : match.getMatchDate() == null;
  }

  @Override
  public int hashCode() {
    return getMatchDate() != null ? getMatchDate().hashCode() : 0;
  }
}
