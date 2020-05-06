package main.domain.manageEvents;

import main.domain.Asset.TeamMember;

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

    public void setTime(Date time)
    {
        this.time = time;
    }

    public void setMinute(int minute)
    {
        this.minute = minute;
    }

    public void setTeamMember(TeamMember teamMember)
    {
        this.teamMember = teamMember;
    }

    public void setType(EventType type) {
        this.type = type;
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

    public EventType getType()
    {
        return type;
    }
}

enum EventType
{
    Goal, Offside, Foul, RedCard, YellowCard, Injury, ReplacementIn, ReplacementOut
}
