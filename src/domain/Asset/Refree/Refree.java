package domain.Asset.Refree;

import DataAccess.GamesDaoSql;
import DataAccess.IfaDaoSql;
import DataAccess.RefreesDaoSql;
import domain.Asset.Coach;
import domain.Asset.Player;
import domain.Asset.TeamMember;
import domain.manageUsers.Account;
import domain.manageUsers.User;
import domain.manageLeagues.Game;
import domain.manageEvents.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public abstract class Refree extends User
{
    protected List<Game> games;
    protected String training;

    protected static RefreesDaoSql refreesDaoSql;
    protected static IfaDaoSql ifaDaoSql;
    protected static GamesDaoSql gamesDaoSql;

    public Refree(String name, Account account, String trainig, String type) throws SQLException {
        super(name,account);
        this.training=trainig;
        String [] key ={account.getUserName(),name,trainig,type};
        refreesDaoSql.save(key);
    }

    public static Refree createRefreeFromDB(String [] key)
    {
        return null;
    }
    public static Refree getRefreeFromDB(String params)
    {
        return null;
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

    public void creareReport(int gameId) throws Exception {
        Game game =Game.getGameFromDB(gameId);
        int host=0;
        int guest=0;
        if(this.account.getUserName().equals(game.getMainRefree()))
        {
            TreeMap<Player,List<Event>> playerEvents=getPlayerEvents(game.getEvents());
            for (Player player: playerEvents.keySet())
            {
                boolean update=false;
                for (Event event:playerEvents.get(player))
                {
                    if(event.getType().equals("Goal"))
                    {
                        player.setGoals(1);
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
                    }
                    else if(event.getType().equals("RedCard"))
                    {
                        player.setRedCards(1);
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
            String result =game.getHost()+": "+host+game.getGuest()+": "+guest;
            game.SetResult(result);
        }
        else {
            throw new Exception("Invalid operation, this will be reported");
        }
    }

    private TreeMap<Player,List<Event>> getPlayerEvents(List<Event> events)
    {
        TreeMap<Player, List<Event>> playersEvents =new TreeMap<>();
        for (Event event:events)
        {
            if(event.getTeamMember() instanceof Coach)
            {
                // send notification to ifa
            }
            else
            {
                if(playersEvents.containsKey((Player) event.getTeamMember()))
                {
                    playersEvents.get((Player) event.getTeamMember()).add(event);
                }
                else
                {
                    List<Event> playerEvent = new LinkedList<>();
                    playerEvent.add(event);
                    playersEvents.put((Player) event.getTeamMember(),playerEvent);
                }
            }

        }
        return playersEvents;
    }

    public void addEvent(String teamMemberUserName, int gameID, String event, int minute, Date date) throws Exception {
        GameEventLog gameEventLog = new GameEventLog(gameID);
        String[] key={"Key",String.valueOf(gameID)};
        List<String[]> games= gamesDaoSql.get(key);
        if(games==null|| games.isEmpty())
        {
            throw new Exception("Invalid game");
        }
        String[]gameData=games.get(0);
        TeamMember teamMember =TeamMember.createTeamMemberFromDB(teamMemberUserName);
        if(gameData[1].equals(teamMember.team.getName())|| gameData[2].equals(teamMember.team.getName()))
        {
            gameEventLog.createEvent(teamMember,event,minute,date);
        }
    }


}
