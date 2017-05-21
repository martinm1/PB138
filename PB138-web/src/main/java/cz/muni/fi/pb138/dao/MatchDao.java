package cz.muni.fi.pb138.dao;

import cz.muni.fi.pb138.entity.Match;
import cz.muni.fi.pb138.entity.Team;

import java.util.List;

/**
 * Created by Adam Laurenčík on 8.5.2017.
 */
public interface MatchDao {

    /**
     * Retrieves all matches from the database
     * @return all matches from the database
     */
    List<Match> getAllMatches();

    /**
     * Returns all matches against concrete team
     * @param team team from database
     * @return matches against team defined in the parameter
     */
    List<Match> getAllMatchesAgainstTeam(Team team);

    /**
     * Returns all home matches of HC Sparta Praha
     * @return all home matches of HC Sparta Praha
     */
    List<Match> getAllHomeMatches();

    /**
     * Returns all away matches of HC Sparta Praha
     * @return all away matches of HC Sparta Praha
     */
    List<Match> getAllAwayMatches();

    /**
     * Counts specatators on all matches
     * @return amount of spectators on all matches
     */
    int countTotalSpectators();

    /**
     * Return an match whose id is the one in parameter
     * @param id id of the match
     * @return Match with id from parameter
     */
    Match findMatchById(Long id);

    /**
     * Counts all shots of HC Sparta Praha team
     * @return number of shots of HC Sprata Praha team
     */
    int countTotalShots();
}
