package domain.manageLeagues;

import domain.manageTeams.TeamInfo;


public interface LeagueCalculator
{
  public int addPointsPerWin();
  public int addPonintsDraw();
  public int addPointsLose();
  public int compareTeams(TeamInfo teamInfo1, TeamInfo teamInfo2);
  public String getName() ;

}
