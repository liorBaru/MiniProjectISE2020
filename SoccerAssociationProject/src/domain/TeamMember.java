package domain;

import java.util.Date;


public abstract class TeamMember extends StaffMember implements pageable
{
    protected Date contract;
    protected double salary;
    protected Page page;

    public TeamMember(Account account, String name,Team team, Date contract, double salary)
    {
        super(account, name, team);
        this.contract = contract;
        this.salary = salary;
    }
    public void setTeam(Team team, Date contract, double salary)
    {
        this.team=team;
        this.contract=contract;
        this.salary=salary;
    }


    @Override
    public void removeTeam(Team team)
    {
        if(this.team.getName()==team.getName())
        {
            team.removeAsset(this);
            team.removeStaffMember(this);
            this.team=null;
        }
    }

    @Override
    public void uploadDataToPage(String data)
    {
        if(data.isEmpty()==false)
        {
            page.addDataToPage(data);
        }
    }


}

