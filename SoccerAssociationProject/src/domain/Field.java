package domain;

import java.util.ArrayList;

public class Field implements Asset {

    String fieldName;
    ArrayList<Team> teams;

    public Field(String fieldName) {
        this.fieldName=fieldName;
        teams=new ArrayList<>();
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void setTeam(Team team) {
        if(team==null)
            return;
        teams.add(team);
    }

    @Override
    public void removeTeam() {

    }

    @Override
    public String getType() {
        return "Field: " +this.fieldName;
    }
}
