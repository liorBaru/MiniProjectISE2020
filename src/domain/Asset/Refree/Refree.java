package domain.Asset.Refree;

import DataAccess.GamesDaoSql;
import DataAccess.IfaDaoSql;
import DataAccess.RefreesDaoSql;
import DataAccess.System;
import domain.Asset.Coach;
import domain.Asset.Player;
import domain.Asset.TeamMember;
import domain.manageUsers.Account;
import domain.manageUsers.User;
import domain.manageLeagues.Game;
import domain.manageEvents.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public abstract class Refree extends User
{
    protected List<Game> games;
    protected String training;

    protected static RefreesDaoSql refreesDaoSql=RefreesDaoSql.getInstance();
    protected static IfaDaoSql ifaDaoSql=IfaDaoSql.getInstance();
    protected static GamesDaoSql gamesDaoSql=GamesDaoSql.getInstance();

    public Refree(String name, Account account, String trainig, String type) throws SQLException {
        super(name,account);
        this.training=trainig;
        String [] key ={account.getUserName(),name,trainig,type};
        refreesDaoSql.save(key);
    }

    public Refree(Account account,String [] params)
    {
        super(params[1],account);
        this.training=params[2];
    }

    public static Refree createRefreeFromDB(String [] key) throws Exception {
        System system= System.getInstance();
        Account account=system.getAccountManager().getAccount(key[0]);
        Refree refree=null;
        if(key[3].equals("Main"))
        {
            refree =new MainRefree(account,key);

        }
        else if(key[3].equals("Line"))
        {
            refree=new LineRefree(account,key);
        }
        else if(key[3].equals("Var"))
        {
            refree= new VarRefree(account,key);
        }
        else
        {
            throw new Exception("Invalid refree type");
        }
        return refree;
    }

    public static Refree getRefreeFromDB(String params) throws Exception {
        if(params!=null && params.isEmpty()==false)
        {
            String [] key={params};
            List<String[]> refrees =refreesDaoSql.get(key);
            if(refrees!=null && refrees.isEmpty()==false)
            {
                return createRefreeFromDB(refrees.get(0));
            }
        }
            throw new Exception("Invalid arguments, username not found");
    }

    @Override
    public boolean removeUser() throws Exception {
        String details = "Refree, "+this.name+", has been remove from the system by the system manager";
        Notification notification = new Notification(details);
        for (String[] ifa:ifaDaoSql.getAll())
        {
            try
            {
                system.sendNotification(ifa[0],notification);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public LinkedList<String> showPersonalDetails()
    {
        LinkedList<String> userDetails= super.showPersonalDetails();
        userDetails.addLast(training);
        userDetails.addFirst(getKind());
        return userDetails;
    }

    public void creareReport(int gameId) throws Exception
    {

        Game game =Game.getGameFromDB(gameId);
        // check time 5 hours
        if(game.getReported()==false)
        {
            if(this.account.getUserName().equals(game.getMainRefree().getAccount().getUserName()))
            {
                int host=0;
                int guest=0;
                TreeMap<Player,LinkedList<Event>> playerEvents=getPlayerEvents(game.getEvents());
                for (Player player: playerEvents.keySet())
                {
                    boolean update=false;
                    for (Event event:playerEvents.get(player))
                    {
                        if(event.getType().equals("Goal"))
                        {
                            player.setGoals(1);
                            update=true;
                            if(game.getHost().equals(player.getTeam()))
                            {
                                host++;
                            }
                            else
                            {
                                guest++;
                            }
                        }
                        else if(event.getType().equals("YellowCard"))
                        {
                            player.setYellowCards(1);
                            update=true;
                        }
                        else if(event.getType().equals("RedCard"))
                        {
                            player.setRedCards(1);
                            update=true;
                        }
                        else if(event.getType().equals("OwnGoal"))
                        {
                            if(game.getHost().equals(player.getTeam()))
                            {
                                guest++;
                            }
                            else
                            {
                                host++;
                            }
                        }
                    }
                    if(update==true)
                    {
                        player.updateByRefree();
                    }
                }
                String result =game.getHost().getName()+": "+host+" "+game.getGuest().getName()+": "+guest;
                game.SetResult(result);
                return;
            }
            else
            {
                throw new Exception("Invalid operation, this will be reported");
            }
        }
        else
        {
            throw new Exception("Invalid operation, game already reported");
        }

    }

    private TreeMap<Player,LinkedList<Event>> getPlayerEvents(List<Event> events)
    {
        TreeMap<Player, LinkedList<Event>> playersEvents =new TreeMap<>();
        for (Event event:events)
        {
            if(event.getTeamMember() instanceof Coach)
            {
                // send notification to ifa
            }
            else
            {
                if(playersEvents.isEmpty()==false &&playersEvents.containsKey((Player) event.getTeamMember()))
                {
                    playersEvents.get(event.getTeamMember()).add(event);
                }
                else
                {
                    LinkedList<Event> playerEvent = new LinkedList<>();
                    playerEvent.add(event);
                    Player p=(Player)  event.getTeamMember();
                    playersEvents.put(p,playerEvent);
                }
            }

        }
        return playersEvents;
    }

    public void addEvent(String teamMemberUserName, int gameID, String event, int minute, Date date) throws Exception {
        GameEventLog gameEventLog = new GameEventLog(gameID);
        String[] key={"Key",String.valueOf(gameID)};
        List<String[]> games= gamesDaoSql.get(key);
        if(games==null || games.isEmpty())
        {
            throw new Exception("Invalid game");
        }
        // add check if refree is refree in the game
        String[]gameData=games.get(0);
        Game.notifyEvent(gameID,event,date);
        TeamMember teamMember =TeamMember.createTeamMemberFromDB(teamMemberUserName);
        if(gameData[1].equals(teamMember.team.getName())|| gameData[2].equals(teamMember.team.getName()))
        {
            gameEventLog.createEvent(teamMember,event,minute,date);
        }
    }
}
