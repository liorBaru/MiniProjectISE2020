package domain.manageLeagues;

import domain.manageTeams.TeamInfo;

public class LeaguePointsCalculator implements LeagueCalculator {

    private int winPoints;
    private int drawPoints;
    private int  losePoints;
    private String name;

    /**
     * @author: David Zaltsman
     * @desc: constructor of season policy
     * @param winPoints - points team gets per winning match
     * @param drawPoints - points team gets per draw match
     * @param losePoints - points team gets per losing match
     */
    public LeaguePointsCalculator(int winPoints , int drawPoints , int losePoints ,String name)
    {
        this.winPoints=winPoints;
        this.drawPoints=drawPoints;
        this.losePoints=losePoints;
        this.name=name;
    }

    /**
     * @author: David Zaltsman
     * @desc: get add points to win
     */
    @Override
    public int addPointsPerWin() {
        return winPoints;
    }

    /**
     * @author: David Zaltsman
     * @desc: return draw points
     */
    @Override
    public int addPonintsDraw() {
        return drawPoints;
    }

    /**
     * @author: David Zaltsman
     * @desc: return lost points
     */
    @Override
    public int addPointsLose() {
        return losePoints;
    }

    @Override
    public int compareTeams(TeamInfo teamInfo1, TeamInfo teamInfo2) {
        if(teamInfo1.getPoints()>teamInfo2.getPoints())
        {
            return 1;
        }
        if(teamInfo2.getPoints()>teamInfo1.getPoints())
        {
            return -1;
        }
        if(teamInfo1.getGoalsDifference()>teamInfo2.getGoalsDifference())
        {
            return 1;
        }
        if(teamInfo1.getGoalsDifference()<teamInfo2.getGoalsDifference())
        {
            return -1;
        }
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    /**
     * @author: chen arazi
     * @desc: return LeagueCalculator
     */

}
