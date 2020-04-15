package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SeasonInfo
{
    private GameScheduale gameScheduale;
    private LeagueCalcolator leagueCalcolator;
    private TreeMap<Integer, List<Game>> games;
    private List <TeamInfo> table;


    public SeasonInfo(GameScheduale gameScheduale, LeagueCalcolator leagueCalcolator) {
        this.gameScheduale = gameScheduale;
        games=new TreeMap<>();
        gameScheduale=this.gameScheduale;
        table=new ArrayList<>();
    }

    public void addTeamInfo(TeamInfo team)
    {
        table.add(team);
    }

    public void addGamePerRound(int round , List<Game> games)
    {
        this.games.put(round,games);
    }


}
