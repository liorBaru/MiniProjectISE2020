package main.domain.Asset;

import main.DB.StaffMembersDaoSql;
import main.domain.managePages.Page;
import main.domain.manageUsers.Account;
import main.domain.manageTeams.Team;
import main.domain.managePages.pageable;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public abstract class TeamMember extends StaffMember implements pageable
{
    protected Page page;
    private static StaffMembersDaoSql staffMembersDaoSql;

    public TeamMember(Account account, String name) throws SQLException
    {
        super(account, name);
        page=new Page(this);
    }

    protected TeamMember()
    {

    }

    public static TeamMember createTeamMemberFromDB(String userName) throws Exception {
        String[] key={userName};
        List<String[]> teamMembers=staffMembersDaoSql.get(key);
        if(teamMembers.isEmpty())
        {
            throw new Exception("wrong user name");
        }
        String[] teamMember=teamMembers.get(0);
        TeamMember teamMember1=null;
        if(teamMember[1].equals("Coach"))
        {
            teamMember1=Coach.createCoachFromDB(key);
        }
        else if(teamMember[1].equals("Player"))
        {
            teamMember1=Player.createPlayerFromDB(key);
        }
        else
        {
            throw new Exception("wrong input");
        }
        return teamMember1;
    }

    public Page getPage()
    {
       return page;
    }

    @Override
    public void removeTeam(Team team) throws SQLException {
        if(this.team!=null)
        {
            if (this.team!=null)
            {
                this.team=null;
            }

        }
        update();
    }

    @Override
    public boolean removeUser() throws Exception
    {
        this.removeTeam(this.team);
        this.page.deletePage();
        return true;
    }

    /**
     * gal
     * upload data to page
     * @param data
     */
    @Override
    public void uploadDataToPage(String data) throws Exception {
        if(data.isEmpty()==false)
        {
            page.addDataToPage(data);
        }
    }
    @Override
    public String getPageOwnerName()
    {
        return this.account.getUserName();
    }

    public List<String>showPersonalDetails()
    {
        List<String> userDetails =super.showPersonalDetails();
        String pageName = "Page: "+page.getPageName();
        userDetails.add(pageName);
        return userDetails;
    }



}
