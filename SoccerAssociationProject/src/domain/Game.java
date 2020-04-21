package domain;

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


    @Override
    public boolean addFollower(User user) throws Exception {
        if(finishDate==null)
        {
            return super.addFollower(user);
        }
        return false;
    }
}
