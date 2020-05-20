package main.domain.manageLeagues;

import main.domain.Asset.Field;
import main.domain.Asset.Refree.Refree;
import main.domain.manageEvents.Notification;
import main.domain.manageUsers.User;
import main.domain.manageEvents.GameEventLog;
import main.domain.managePages.Subject;
import main.domain.manageTeams.Team;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    private Refree[] referees;

    public List<String> getTeams ()
    {
        List<String> teams=new LinkedList<>();
        teams.add(guest.getName());
        teams.add(host.getName());
        return teams;
    }

    public Game (Team guest, Team host, SeasonInfo seasonInfo)
    {
        this.guest=guest;
        this.host=host;
        this.league=seasonInfo;
    }


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

    @Override
    public void notifyObservers(Notification notification)
    {

    }
}
