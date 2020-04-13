package domain;

import java.util.TreeMap;

public abstract class TeamMember extends StaffMember
{
    private int contract;
    private double salary;

    public TeamMember(String userName, String password, String name, String job, Team team, int contract, double salary) {
        super(userName, password, name, job, team);
        this.contract = contract;
        this.salary = salary;
    }

    @Override
    public void removeTeam() {
        if(team!=null) {
            this.team.removeAsset(this);
            team=null;
        }
    }
}

