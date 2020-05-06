package main.domain.manageEvents;

import main.domain.Asset.TeamMember;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GameEventLog
{
    private List<Event> events;

    public  GameEventLog()
    {
        events=new LinkedList<>();
    }

    public void createEvent(TeamMember teamMember, EventType type, int minute, Date date) throws Exception
    {
        if(teamMember!=null && type!=null && minute>0 && date!=null)
        {
            Event event = new Event(teamMember,type,minute,date);
            events.add(event);
        }
        throw new Exception("Invalid arguments");
    }

    public List<Event> getEvents()
    {
        return events;
    }



}
