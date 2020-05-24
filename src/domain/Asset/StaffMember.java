package domain.Asset;

import DataAccess.StaffMembersDaoSql;
import domain.manageTeams.Team;
import domain.manageUsers.Account;
import domain.manageUsers.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class StaffMember extends User implements Asset
{
    public Team team;
    protected BoardMember boss;
    protected StaffMembersDaoSql staffMembersDaoSql;

    public StaffMember(Account account, String name, Team team, BoardMember boardMember, String type) throws SQLException {
        super(name,account);
        this.team=team;
        this.boss=boardMember;
        String[]key={account.getUserName(),type};
        staffMembersDaoSql.save(key);
    }

    public StaffMember (Account account, String name, Team team)
    {
        super(name,account);
        this.team=team;
    }

    public StaffMember(Account account, String name)
    {
        super(name,account);
    }

    protected StaffMember() {
    }

    public BoardMember getBoss()
    {
        return boss;
    }



    public void setTeam(Team team) throws SQLException {
        this.team = team;
        update();
    }

    public void setBoss(BoardMember boardMember) throws SQLException {
        this.boss=boardMember;
        update();
    }


    @Override
    public abstract String getType();

    public abstract void removeTeam(Team team) throws Exception;

    public Team getTeam() {
        return team;
    }

    /**
     * gal
     * show staffMember personal details
     * @return
     */

    @Override
    public LinkedList<String> showPersonalDetails()
    {
        LinkedList<String> userDetails= super.showPersonalDetails();
        String teamString="";
        if(team!=null)
        {
            teamString = team.getName();
        }
        userDetails.addLast(teamString);
        return userDetails;
    }

    @Override
    public boolean removeUser() throws Exception {
        this.removeTeam(this.team);
        return true;
    }

    @Override
    public boolean equals(Object object)
    {
        if(object instanceof StaffMember)
        {
            StaffMember staffMember = (StaffMember)object;
            if(staffMember.account.getUserName().equals(this.account.getUserName()))
            {
                return true;
            }
        }
        return false;
    }
}
