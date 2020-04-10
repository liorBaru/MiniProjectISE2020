package domain;

import java.util.List;

public class TeamManager extends BoardMember implements Asset
{
    private double salary;

    public TeamManager(String userName, String password, String name, String job, Team team, BoardMember boss, double salary) {
        super(userName, password, name, job, team, boss);
        this.salary = salary;
    }

    @Override
    public String getType() {
        return "Team Manager: "+this.name;
    }
}
