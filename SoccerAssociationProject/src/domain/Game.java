package domain;

import java.util.Date;

public class Game extends Subject
{
    private Team guest;
    private Team host;
    private GameEventLog eventsLog;
    private SeasonInfo league;
    private String field;
    private Date date;
    private GameStatus status;
    private int hScore;
    private int gScore;
    Refree lineReferee1;
    Refree lineReferee2;
    Refree mainReferee;
    Refree extraRefree;
    Refree[] varReferees;








}

enum GameStatus
{
    Active, Cancel, Finish, NotStarted
}