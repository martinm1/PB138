package cz.muni.fi.pb138.dao;

import cz.muni.fi.pb138.entity.Match;
import cz.muni.fi.pb138.entity.Team;

import java.util.List;

/**
 * Created by lauro on 8.5.2017.
 */
public interface MatchDao {

    List<Match> getAllMatches();

    List<Match> getAllMatchesAgainstTeam(Team team);

    List<Match> getAllHomeMatches();

    List<Match> getAllAwayMatches();

    int countTotalSpectators();

    Match findMatchById(Long id);

    int countTotalShots();
}
