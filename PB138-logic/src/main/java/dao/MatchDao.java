package dao;

import cz.muni.fi.pb138.db.entity.Match;
import cz.muni.fi.pb138.db.entity.Team;

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

    int countTotalShots();
}
