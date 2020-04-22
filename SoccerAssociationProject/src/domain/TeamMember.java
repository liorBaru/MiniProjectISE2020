package domain;

import java.util.Date;
import java.util.List;


public abstract class TeamMember extends StaffMember implements pageable
{
    protected Date contract;
    protected double salary;
    protected Page page;

    public TeamMember(Account account, String name)
    {
        super(account, name);
        page=new Page(this);
    }



    public void setTeam(Team team, Date contract, double salary)
    {
        this.team=team;
        this.contract=contract;
        this.salary=salary;
    }

    public Page getPage()
    {
       return page;
    }




    @Override
    public void removeTeam(Team team)
    {
        if(team!=null)
        {
            team.removeAsset(this);
            team.removeStaffMember(this);
            if (this.team!=null)
            {
                this.team=null;
            }

        }
    }

    /**
     * gal
     * upload data to page
     * @param data
     */
    @Override
    public void uploadDataToPage(String data)
    {
        if(data.isEmpty()==false)
        {
            page.addDataToPage(data);
        }
    }

    public List<String>showPersonalDetails()
    {
        List<String> userDetails =super.showPersonalDetails();
        String pageName = "Page: "+page.getPageName();
        String contractString = "Contract: "+contract.toString();
        String salaryString = "Salary: "+ salary;
        userDetails.add(pageName);
        userDetails.add(contractString);
        userDetails.add(salaryString);
        return userDetails;
    }



}

