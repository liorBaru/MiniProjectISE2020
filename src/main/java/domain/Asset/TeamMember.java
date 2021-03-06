package domain.Asset;

import DataAccess.StaffMembersDaoSql;
import domain.managePages.Page;
import domain.managePages.pageable;
import domain.manageTeams.Team;
import domain.manageUsers.Account;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public abstract class TeamMember extends StaffMember implements pageable
{
    protected Page page;
    private static StaffMembersDaoSql staffMembersDaoSql =StaffMembersDaoSql.getInstance();

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
            teamMember1=Coach.getCoachFromDB(key);
        }
        else if(teamMember[1].equals("Player"))
        {
            teamMember1=Player.getPlayerFromDB(key);
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

    public LinkedList<String> showPersonalDetails()
    {
        LinkedList<String> userDetails =super.showPersonalDetails();
        String pageName =page.getPageName();
        userDetails.add(pageName);
        return userDetails;
    }





}
