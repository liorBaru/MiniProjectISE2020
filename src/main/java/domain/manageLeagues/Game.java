package domain.manageLeagues;

import DataAccess.System;
import DataAccess.GameFollwersDaoSql;
import DataAccess.GamesDaoSql;
import domain.Asset.Field;
import DataAccess.*;
import domain.Asset.Refree.Refree;
import domain.Asset.SystemManager;
import domain.manageEvents.Event;
import domain.manageEvents.GameEventLog;
import domain.manageTeams.Team;
import domain.manageUsers.User;
import domain.manageEvents.Notification;
import Logger.*;

import java.sql.SQLException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Game
{
    private int gameID;
    private Team guest;
    private Team host;
    private GameEventLog eventsLog;
    private Field field;
    private Date startDate;
    private String score;
    private String league;
    private LinkedList<Refree> referees;
    private boolean var;
    private boolean reported;
    private static GamesDaoSql gamesDaoSql=GamesDaoSql.getInstance();
    private static GameFollwersDaoSql gameFollwersDaoSql= GameFollwersDaoSql.getInstance();
    private static  NotificationsDaoSql notificationsDaoSql =NotificationsDaoSql.getInstance();

    public Game (Team guest, Team host)
    {
        this.guest=guest;
        this.host=host;
        reported = false;
        var=false;
        eventsLog =new GameEventLog(gameID);
        score="";
        this.league=league;
    }

    public Game(int gameId, Team guest, Team host, Field field,String league ,String score, Date date, LinkedList<Refree> refrees,boolean reported,boolean var)
    {
        this.gameID=gameId;
        this.guest=guest;
        this.host=host;
        this.field=field;
        this.league=league;
        this.score=score;
        this.startDate=date;
        this.referees=refrees;
        this.reported=reported;
        this.var=var;
        eventsLog= new GameEventLog(gameId);
    }

    public static Game getGameFromDB(int gameId) throws Exception
    {
        String [] key ={"Key",String.valueOf(gameId)};
        List<String[]> games=gamesDaoSql.get(key);
        if(games==null || games.isEmpty())
        {
            throw new Exception("Invalid arguments");
        }
        String [] gameData=games.get(0);
        Team guest=Team.createTeamFromDB(gameData[1]);
        Team host=Team.createTeamFromDB(gameData[2]);
        Field field=null;
        if(gameData[3].isEmpty()==false)
        {
            field = new Field(gameData[3]);
        }
        String score=gameData[5];
        Date date=null;
        if(gameData[4].isEmpty()==false)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            date=dateFormat.parse(gameData[4]);
        }
        String league=gameData[6];
        LinkedList<Refree> refrees = new LinkedList<>();
        if(gameData[7].isEmpty()==false)
        {
            Refree main=Refree.getRefreeFromDB(gameData[7]);
            Refree line1=Refree.getRefreeFromDB(gameData[8]);
            Refree line2=Refree.getRefreeFromDB(gameData[9]);
            Refree extra =Refree.getRefreeFromDB(gameData[10]);
            refrees.addLast(main);
            refrees.addLast(line1);
            refrees.addLast(line2);
            refrees.addLast(extra);
        }
        // handle var refrees;
        boolean var=Boolean.valueOf(gameData[11]);
        boolean reported=Boolean.valueOf(gameData[12]);
        Game game = new Game(gameId,guest,host,field,league,score,date,refrees,reported,var);
        return game;
    }

    public Team getGuest() {
        return guest;
    }

    public Team getHost() {
        return host;
    }

    public Refree getMainRefree() throws Exception {
        if(referees!=null)
        {
            return referees.get(0);
        }
        throw new Exception("main refree is not set for the game");
    }

    public void setField(Field field) throws Exception {
        this.field = field;
        update();
    }

    public void setStartDate(Date startDate) throws Exception {
        this.startDate = startDate;
        update();
    }


    public void SetRefrees(LinkedList<Refree> refrees) throws Exception {

        if(refrees==null || refrees.size()<4)
        {
            throw new Exception("Invalid arguments");
        }
        this.referees=refrees;
        if(refrees.size()>4)
        {
            var=true;
            for (int i=4;i<refrees.size();i++)
            {
                /// handle var refrees
            }
        }
        update();
    }

    private void setFollower(String username) throws SQLException {
        String[] key ={String.valueOf(gameID),username};
        gameFollwersDaoSql.save(key);
    }


    public void SetResult(String result) throws Exception {
        this.score=result;
        reported=true;
        update();
    }

    private void update() throws Exception
    {
        String gameId=String.valueOf(gameID);
        String guestS= guest.getName();
        String hostS=host.getName();
        String fieldS="";
        if(field!=null)
        {
            fieldS=field.getName();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date="";
        if(startDate!=null)
        {
            date=dateFormat.format(startDate);
        }
        String mainR="";
        String line1="";
        String line2="";
        String extra="";
        if(referees.isEmpty()==false)
        {
            mainR=referees.get(0).getAccount().getUserName();
            line1=referees.get(1).getAccount().getUserName();
            line2=referees.get(2).getAccount().getUserName();
            extra=referees.get(3).getAccount().getUserName();
        }
        String[] params={gameId,guestS,hostS,fieldS,date,score,league,mainR,line1,line2,extra,String.valueOf(var),String.valueOf(reported)};
        gamesDaoSql.update(params);
    }

    public List<Event> getEvents() throws Exception {
        return eventsLog.createEventsFromDB(host,guest);
    }
    public boolean getReported()
    {
        return reported;
    }

    public static void notifyEvent(int gameId, String event, java.sql.Date date) throws SQLException {
        if(gameId<0 && event!=null && date!=null)
        {
            String[] gameDetails = {"gameID" , String.valueOf(gameId)};
            List<String[] > gameFollower =gameFollwersDaoSql.get(gameDetails);
            if(gameFollower==null)
            {
                Logger.Lo4jDemo.writeError("no users has follow after this game");
                throw new NullPointerException();
            }
            else
            {
                for (String[] userdetails : gameFollower) {
                    Notification notification=null;
                    try {
                        Logger.Lo4jDemo.writeError("Invalid notifications arguments");
                        notification = new Notification(event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    notification.setDate(date);
                    String username = userdetails[1];
                    String [] key ={username,notification.getDetails(),notification.getDate()};
                    System system =System.getInstance();
                    system.sendNotification(username,notification);
                }
            }

        }

    }
}
