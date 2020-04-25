package domain.manageLeagues;

public class LeaguePointsCalculator implements  LeagueCalcolator {

    private int winPoints;
    private int drawPoints;
    private int  losePoints;

    /**
     * @author: David Zaltsman
     * @desc: constructor of season policy
     * @param winPoints - points team gets per winning match
     * @param drawPoints - points team gets per draw match
     * @param losePoints - points team gets per losing match
     */
    public LeaguePointsCalculator(int winPoints , int drawPoints , int losePoints)
    {
        this.winPoints=winPoints;
        this.drawPoints=drawPoints;
        this.losePoints=losePoints;
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
}
