package domain.Asset;

import domain.manageTeams.Team;

public class Field implements Asset {

    private String fieldName;
    private Team team;
    public Field(String fieldName)
    {
        this.fieldName=fieldName;
    }
    public Field (String name, Team team)
    {
        this.team=team;
        this.fieldName=name;
    }


    @Override
    public String getName()
    {
        return fieldName;
    }

    @Override
    public void setTeam(Team team)
    {
        this.team = team;
    }

    @Override
    public void removeTeam(Team team)
    {
       this.team=null;
    }

    @Override
    public String getType()
    {
        return "Field";
    }
}
