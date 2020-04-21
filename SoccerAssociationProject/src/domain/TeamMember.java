package domain;

import java.util.Date;
import java.util.List;


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
    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public TeamMember(Account account, String name)
    {
        super(account,name);
    }
    public void setTeam(Team team, Date contract, double salary)
    {
        this.team=team;
        this.contract=contract;
        this.salary=salary;
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
