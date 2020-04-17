package domain;

import java.util.List;
import java.util.TreeMap;

public class TeamManager extends BoardMember
{
    private double salary;

    public TeamManager(Account account, String name, Team team,String job, BoardMember boss, double salary, List<String> permissions)
    {
        super(account,name,job,team,boss);
        this.salary=salary;
        setPermissions(permissions);
    }
    public TeamManager(User user,Team team,String job,List<String>permissionList,double salary,BoardMember boss){
        super(user.account,user.name,job,team,boss);
        this.salary=salary;
        setPermissions(permissionList);
    }

    @Override
    public String getType() {
        return "Team Manager: "+this.name;
    }

    private void setPermissions(List<String>permissions)
    {
        for (String permission:permissions)
        {
             premission= Enum.valueOf(premission.class, permission);
            this.permissions.put(premission,true);
        }
    }
}
