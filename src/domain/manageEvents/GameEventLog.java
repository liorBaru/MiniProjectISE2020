package domain.manageEvents;

import DataAccess.GameEventsDaoSql;
import domain.Asset.Player;
import domain.Asset.TeamMember;
import domain.manageTeams.Team;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class GameEventLog
{
    private List<Event> events;
    private int gameID;
    private GameEventsDaoSql gameEventsDaoSql =GameEventsDaoSql.getInstance();

    public  GameEventLog(int id)
    {
        this.gameID=id;
        events=new LinkedList<>();
    }

    public void createEvent(TeamMember teamMember, String type, int minute, Date date) throws Exception
    {

        if(teamMember!=null && type!=null && minute>0 && date!=null)
        {
            EventType eventType=EventType.valueOf(type);
            Event event = new Event(teamMember,eventType,minute,date);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String sDate=dateFormat.format(date);
            String[] key={String.valueOf(gameID),teamMember.getAccount().getUserName(),type,String.valueOf(minute),sDate};
            gameEventsDaoSql.save(key);
            events.add(event);
            return;
        }
        throw new Exception("Invalid arguments");
    }

    public void editEvent(TeamMember teamMember, String type, int minute, Date date) throws Exception
    {
        if(teamMember!=null && type!=null && minute>0 && date!=null)
        {
            EventType eventType=EventType.valueOf(type);
            Event event = new Event(teamMember,eventType,minute,date);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String sDate=dateFormat.format(date);
            String[] key={String.valueOf(gameID),teamMember.getAccount().getUserName(),type,String.valueOf(minute),sDate};
            gameEventsDaoSql.save(key);
            events.add(event);
            return;
        }
        throw new Exception("Invalid arguments");
    }

    public List<Event> createEventsFromDB(Team host, Team guest) throws Exception {
        List<TeamMember> hp =host.getTeamMembers();
        List<TeamMember> gp=guest.getTeamMembers();
        TreeMap<String,TeamMember> playerMap=new TreeMap<>();
        for (TeamMember teamMember:hp)
        {
            playerMap.put(teamMember.getAccount().getUserName(),teamMember);
        }
        for (TeamMember teamMember:gp)
        {
            playerMap.put(teamMember.getAccount().getUserName(),teamMember);
        }
        String[] key={String.valueOf(gameID)};
        List<Event> events= new LinkedList<>();
        for (String[] event:gameEventsDaoSql.get(key))
        {
            if(playerMap.containsKey(event[1]))
            {
                TeamMember teamMember=playerMap.get(event[1]);
                String type=event[2];
                int minute =Integer.valueOf(event[3]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date=dateFormat.parse(event[4]);
                EventType eventType=EventType.valueOf(type);
                Event event1 = new Event(teamMember,eventType,minute,date);
                events.add(event1);
            }
            else
            {
                throw new Exception("Invalid team member event, team member not in the team");
            }
        }
        return events;
    }

    public List<Event> getEvents()
    {
        return events;
    }



}
