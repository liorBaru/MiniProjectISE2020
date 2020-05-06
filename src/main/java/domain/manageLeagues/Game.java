package domain.manageLeagues;

import domain.Asset.Field;
import domain.Asset.Refree.Refree;
import domain.manageUsers.User;
import domain.manageEvents.GameEventLog;
import domain.manageLeagues.SeasonInfo;
import domain.managePages.Subject;
import domain.manageTeams.Team;

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
    Refree lineReferee1;
    Refree lineReferee2;
    Refree mainReferee;
    Refree extraRefree;
    Refree[] varReferees;


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
