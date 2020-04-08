package domain;

import java.util.List;
import java.util.TreeMap;

public interface GameScheduale
{
    public TreeMap<Integer, List<Game>> createLeagueGames(SeasonInfo season);
}
