package domain.manageLeagues;

import java.util.List;
import java.util.TreeMap;

public interface GameScheduale
{
     TreeMap<Integer, List<Game>> createLeagueGames(SeasonInfo season);
}
