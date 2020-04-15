package domain;

import java.util.List;
import java.util.TreeMap;

public class TeamManager extends BoardMember
{
    private double salary;

    public TeamManager(Account account, String name, Team team, BoardMember boss, double salary, List<String> premissions)
    {
        super(account,name,team,boss);
        this.salary=salary;
        setPermissions(premissions);
    }

    @Override
    public String getType() {
        return "Team Manager: "+this.name;
    }

    private void setPermissions(List<String>permissions)
    {
        for (String permission:permissions)
        {
            premission premission= Enum.valueOf(premission.class, permission);
            this.permissions.put(premission,true);
        }
    }
}
