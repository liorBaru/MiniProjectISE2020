package domain;

import java.util.ArrayList;

public class Field implements Asset {

    private String fieldName;
    private ArrayList<Team> teams;

    public Field(String fieldName)
    {
        this.fieldName=fieldName;
        teams=new ArrayList<>();
    }

    @Override
    public String getName()
    {
        return fieldName;
    }

    @Override
    public void setTeam(Team team)
    {
        if(team!=null && teams.contains(team)==false)
        {
            teams.add(team);
        }
    }

    @Override
    public void removeTeam(Team team)
    {
        if (team != null && teams.contains(team))
        {
            teams.remove(team);
        }
    }

    @Override
    public String getType()
    {
        return "Field: " +this.fieldName;
    }
}
