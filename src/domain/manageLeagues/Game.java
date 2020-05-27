package domain.manageLeagues;

import DataAccess.System;
import com.sun.deploy.security.CertStore;
import domain.Asset.Field;
import DataAccess.*;
import domain.Asset.Refree.Refree;
import domain.Asset.SystemManager;
import domain.manageEvents.Event;
import domain.manageEvents.GameEventLog;
import domain.managePages.Subject;
import domain.manageTeams.Team;
import domain.manageUsers.User;
import domain.manageEvents.Notification;
import Logger.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Game extends Subject
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
    private boolean reported;

    private static GamesDaoSql gamesDaoSql;
    private static GameFollwersDaoSql gameFollwersDaoSql;
    private static  NotificationsDaoSql notificationsDaoSql;

    public Game (Team guest, Team host)
    {
        this.guest=guest;
        this.host=host;
        reported = false;
    }

    public Game(int gameId, Team guest, Team host, Field field, String score, Date date, LinkedList<Refree> refrees,boolean reported)
    {
        this.gameID=gameId;
        this.guest=guest;
        this.host=host;
        this.field=field;
        this.score=score;
        this.startDate=date;
        this.referees=refrees;
        this.reported=reported;
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
        Field field = new Field(gameData[3]);
        String score=gameData[5];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=dateFormat.parse(gameData[4]);
        String league=gameData[6];
        Refree main=Refree.getRefreeFromDB(gameData[7]);
        Refree line1=Refree.getRefreeFromDB(gameData[8]);
        Refree line2=Refree.getRefreeFromDB(gameData[9]);
        Refree extra =Refree.getRefreeFromDB(gameData[10]);
        LinkedList<Refree> refrees = new LinkedList<>();
        refrees.addLast(main);
        refrees.addLast(line1);
        refrees.addLast(line2);
        refrees.addLast(extra);
        // handle var refrees;
        boolean reported=Boolean.valueOf(gameData[11]);
        Game game = new Game(gameId,guest,host,field,score,date,refrees,reported);
        return game;
    }



    public Team getGuest() {
        return guest;
    }

    public void setGuest(Team guest) {
        this.guest = guest;
    }

    public Team getHost() {
        return host;
    }

    public void setHost(Team host) {
        this.host = host;
    }





    @Override
    public void notifyObservers(Notification notification)
    {

    }

    public Refree getMainRefree() throws Exception {
        if(referees!=null)
        {
            return referees.get(0);
        }
        throw new Exception("main refree is not set for the game");
    }

    public void SetRefrees(LinkedList<Refree> refrees) throws Exception {

        if(refrees==null || refrees.size()<4)
        {
            throw new Exception("Invalid arguments");
        }
        String[] key=new String[7];
        key[0]="Refrees";
        key[1]=String.valueOf(gameID);
        key[2]=refrees.get(0).getAccount().getUserName();
        key[3]=refrees.get(1).getAccount().getUserName();
        key[4]=refrees.get(2).getAccount().getUserName();
        key[5]=refrees.get(3).getAccount().getUserName();
        boolean var=false;
        if(refrees.size()>4)
        {
            var=true;
            for (int i=4;i<refrees.size();i++)
            {
                // GameVarRfree push DB
            }
        }
        key[6]=String.valueOf(var);
        gamesDaoSql.update(key);
    }


    public void SetResult(String result) throws Exception {
        this.score=result;
        String [] key ={"Result",result};
        update(key);
    }

    private void update(String [] key) throws Exception {
        gamesDaoSql.update(key);
    }

    public List<Event> getEvents() throws Exception {
        return eventsLog.createEventsFromDB(host,guest);
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
