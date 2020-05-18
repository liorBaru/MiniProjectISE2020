package main.domain.Asset;

import main.domain.manageUsers.Account;
import main.domain.manageTeams.Team;

import java.util.List;

public class TeamManager extends BoardMember
{

    private double salary;

    public TeamManager(Account account, String name, Team team, BoardMember boss, double salary, List<String> permissions)
    {
        super(account,name,team,boss);
        this.salary=salary;
        setPermissions(permissions);
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public String getType() {
        return "Team Manager: "+this.name;
    }

    public void setPermissions(List<String>permissions)
    {
        if (permissions != null)
        {
            for (String permission : permissions)
            {
                permission permissionToAdd = Enum.valueOf(permission.class, permission);
                this.permissions.put(permissionToAdd, true);
            }

        }
        permission[] possibleValues = permission.values();
        for (int i=0; i<possibleValues.length;i++)
        {
            if(this.permissions.containsKey(possibleValues[i])==false)
            {
                this.permissions.put(possibleValues[i],false);
            }
        }
    }
}