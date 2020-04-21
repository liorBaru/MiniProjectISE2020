package domain;

import java.util.List;

public class TeamManager extends BoardMember
{
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private double salary;

    public TeamManager(Account account, String name, Team team,String job, BoardMember boss, double salary, List<String> permissions)
    {
        super(account,name,team);
        this.salary=salary;
        setPermissions(permissions);
    }
    public TeamManager(User user,Team team,String job,List<String>permissionList,double salary){
        super(user.account,user.name,team);
        this.salary=salary;
        setPermissions(permissionList);
    }

    @Override
    public String getType() {
        return "Team Manager: "+this.name;
    }

    protected void setPermissions(List<String>permissions) {
        if (permissions != null) {
            for (String permission : permissions) {
                domain.permission permissionToAdd = Enum.valueOf(domain.permission.class, permission);
                this.permissions.put(permissionToAdd, true);
            }
        }
    }
}
