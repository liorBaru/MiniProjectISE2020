package main.domain.Asset;


import main.DB.ApointmentsDaoSql;
import main.DB.AssetsDauSql;
import main.DB.PermissionsDaoSql;
import main.DB.StaffMembersDaoSql;
import main.domain.manageUsers.Account;
import main.domain.manageTeams.FinancialAction;
import main.domain.manageTeams.Team;
import main.domain.manageUsers.User;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public abstract class BoardMember extends StaffMember
{
    protected  LinkedList<permission> permissions;
    protected static ApointmentsDaoSql apointmentsDaoSql;
    protected static PermissionsDaoSql permissionsDaoSql;
    protected static StaffMembersDaoSql staffMembersDaoSql;
    protected static AssetsDauSql assetsDauSql;

    public BoardMember (Account account, String name, Team team, BoardMember boss,String type) throws SQLException {
        super(account,name,team,boss,type);
        permissions= new LinkedList<>();
    }

    public BoardMember(Account account, String name, Team team)
    {
        super(account,name,team);
        permissions= new LinkedList<>();
    }

    protected BoardMember()
    {
    }

    /**
     * gal
     * permission : remove player
     * @param player
     * @return
     */
    public boolean removePlayer(Player player)
    {
        if(player!=null && permissions.contains(permission.removePlayer) && team!=null)
        {
            team.removeStaffMember(player);
            player.setTeam(null);
            return true;
        }
        return false;
    }
    /**
     * gal
     * permission : add couch
     * @param couch
     * @return
     */
    public boolean addCouch(Coach couch) throws SQLException
    {
        if(couch!=null && permissions.contains(permission.addCoach)&&team!=null)
        {
            team.addStaffMember(couch);
            String[] key={this.account.getUserName(),couch.getAccount().getUserName()};
            apointmentsDaoSql.save(key);
            return true;
        }
        return false;
    }

    /**
     * permission : remove coach
     * @param coach
     * @return
     */
    public boolean removeCoach(Coach coach)
    {
        if(coach!=null && permissions.contains(permission.removeCoach)&&team!=null)
        {
            String [] key ={this.account.getUserName(),coach.getAccount().getUserName()};
            apointmentsDaoSql.delete(key);
            team.removeStaffMember(coach);
            coach.setTeam(null);
            return true;
        }
        return false;
    }

    /**
     * permission :add financial action
     * @param description
     * @param price
     * @return
     */

    public boolean addFinancialAction(String description, double price)
    {
        if(permissions.contains(permission.addFinancial)&&team!=null)
        {
            FinancialAction financialAction = new FinancialAction(description,price,this);
            system.sendFinancialAction(this,financialAction);
        }
        return false;
    }

    /**
     * permission: add assets to team
     * @param name
     * @return
     */

    public boolean addAssets(String name,String type) throws SQLException {
        if(permissions.contains(permission.addAsset)&&team!=null)
        {
            if(type.equals("Field"))
            {
                String[]key={team.getName(),name,"Field"};
                assetsDauSql.save(key);
                return true;
            }
        }
        return false;
    }

    public void removeAsset(String name) throws Exception {
        if(permissions.contains(permission.removeAsset)&&team!=null)
        {
            String[]key={"key",team.getName(),name};
            assetsDauSql.delete(key);
        }
        throw new Exception("invalid operation , check your premission");
        //TODO: write to logger
    }

    /**
     * permissions: update team page
     * @param message
     * @return
     */

    public boolean updateTeamPage (String message) throws Exception {
        if(permissions.contains(permission.updateTeamPage)&&team!=null)
        {
            team.uploadDataToPage(message);
            return true;
        }
        return false;
    }

    public List<StaffMember> getAppointments() throws Exception {
        String[] key ={"Boss",this.account.getUserName()};
        List<StaffMember> staffMembers = new LinkedList<>();
        for (String[] apointment:apointmentsDaoSql.get(key))
        {
            String [] detailes={apointment[1]};
            List<String[]> staffMembersL =staffMembersDaoSql.get(detailes);
            String[] staffMember=staffMembersL.get(0);
            User user =system.getAccountManager().getUser(staffMember[0],staffMember[1]);
            if(user instanceof StaffMember)
            {
                staffMembers.add((StaffMember) user);
            }
        }
        return staffMembers;
    }

    @Override
    public void removeTeam(Team team) throws Exception {
        if(team!=null)
        {
            for (StaffMember appoint:getAppointments())
            {
                appoint.removeTeam(team);
            }
            if(this.team!=null)
            {
                this.team.removeStaffMember(this);
                this.team=null;
            }
        }
        update();
    }

    public void cleanPermission()
    {
        String [] key = {"user_name",this.account.getUserName()};
        permissionsDaoSql.delete(key);
    }



}

