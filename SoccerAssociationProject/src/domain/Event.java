package domain;

import java.util.Date;

public class Event
{
    private Date time;
    private int minute;
    private TeamMember teamMember;
    private EventType type;

    public Event (TeamMember teamMember, EventType type, int minute)
    {
        this.teamMember=teamMember;
        this.type=type;
        this.minute=minute;
        time=new Date();
    }





}

enum EventType
{
    Goal, Offside, Foul, RedCard, YellowCard, Injury, ReplacementIn, ReplacementOut
}
