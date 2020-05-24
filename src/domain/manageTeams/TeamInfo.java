package domain.manageTeams;

import domain.manageLeagues.SeasonInfo;

public class TeamInfo
{
    private Team team;
    private SeasonInfo seasonInfo;
    private int points;
    private int goals;
    private int oGoals;
    private int losses;
    private int victories;
    private int draw;
    private int position;


    public int getPoints()
    {
        return points;
    }

    public int getGoalsDifference()
    {
        return goals-oGoals;
    }
}
