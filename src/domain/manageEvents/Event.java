package domain.manageEvents;

import domain.Asset.TeamMember;

import java.util.Date;

public class Event
{
    private Date time;
    private int minute;
    private TeamMember teamMember;
    private EventType type;

    public Event (TeamMember teamMember, EventType type, int minute , Date date)
    {
        this.teamMember=teamMember;
        this.type=type;
        this.minute=minute;
        time=date;
    }





    public Date getTime() {
        return time;
    }

    public int getMinute() {
        return minute;
    }

    public TeamMember getTeamMember()
    {
        return teamMember;
    }

    public String getType()
    {
        return type.name();
    }
}

enum EventType
{
    Goal, Offside, Foul, RedCard, YellowCard, Injury, ReplacementIn, ReplacementOut
}
