package main.domain.manageLeagues;

import main.domain.manageTeams.Team;
import main.domain.manageTeams.TeamInfo;


public interface LeagueCalculator
{
  public int addPointsPerWin();
  public int addPonintsDraw();
  public int addPointsLose();
  public int compareTeams(TeamInfo teamInfo1, TeamInfo teamInfo2);
  public String getName() ;

}
