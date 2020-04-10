package domain;

import java.util.TreeMap;

public abstract class TeamMember extends StaffMember implements Asset
{
    private int contract;
    private double salary;

    public TeamMember(String userName, String password, String name, String job, Team team, int contract, double salary) {
        super(userName, password, name, job, team);
        this.contract = contract;
        this.salary = salary;
    }
}

