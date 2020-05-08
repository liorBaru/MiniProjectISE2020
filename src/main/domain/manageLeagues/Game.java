package main.domain.manageLeagues;

import main.domain.Asset.Field;
import main.domain.Asset.Refree.Referee;
import main.domain.manageUsers.User;
import main.domain.manageEvents.GameEventLog;
import main.domain.managePages.Subject;
import main.domain.manageTeams.Team;
import java.util.Date;

public class Game extends Subject
{
    private Team guest;
    private Team host;
    private GameEventLog eventsLog;
    private SeasonInfo league;
    private Field field;
    private Date startDate;
    private Date finishDate;
    private int hScore;
    private int gScore;
    Referee lineReferee1;
    Referee lineReferee2;
    Referee mainReferee;
    Referee extraReferee;
    Referee[] varReferees;

    /**
     * add follower to the game
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public boolean addFollower(User user) throws Exception {
        if(user!=null && finishDate==null)
        {
            return super.addFollower(user);
        }
        return false;
    }
}
