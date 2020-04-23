package domain.manageLeagues;

import domain.manageTeams.TeamInfo;

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



    public void setGameScheduale(GameScheduale gameScheduale) {
        this.gameScheduale = gameScheduale;
    }

    public void setLeagueCalcolator(LeagueCalcolator leagueCalcolator) {
        this.leagueCalcolator = leagueCalcolator;
    }

}
