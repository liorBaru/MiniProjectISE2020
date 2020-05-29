package domain.Asset;

import domain.manageTeams.FinancialAction;
import domain.manageTeams.Team;
import domain.manageUsers.Account;
import domain.manageUsers.User;
import Logger.Lo4jDemo;
import DataAccess.ApointmentsDaoSql;
import DataAccess.AssetsDauSql;
import DataAccess.PermissionsDaoSql;
import DataAccess.StaffMembersDaoSql;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public abstract class BoardMember extends StaffMember
{
    protected LinkedList<permission> permissions;
    protected static ApointmentsDaoSql apointmentsDaoSql=ApointmentsDaoSql.getInstance();
    protected static PermissionsDaoSql permissionsDaoSql =PermissionsDaoSql.getInstance();
    protected static StaffMembersDaoSql staffMembersDaoSql=StaffMembersDaoSql.getInstance();
    protected static AssetsDauSql assetsDauSql=AssetsDauSql.getInstance();

    public BoardMember (Account account, String name, Team team, BoardMember boss, String type) throws SQLException {
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
     * permission : add player
     * @param player
     * @return
     */

    public boolean addPlayer(Player player) throws Exception {
        if(player!=null && player.team==null &&team!=null)
        {
            player.setTeam(team);
            return true;
        }
        Lo4jDemo.writeError("Invalid operation - addPlayer "+this.account.getUserName());
        throw new Exception("Invalid operation");
    }

    /**
     * gal
     * permission : remove player
     * @param player
     * @return
     */
    public boolean removePlayer(Player player) throws Exception {
        if(player!=null && permissions.contains(permission.removePlayer) && team!=null)
        {
            if(this.team.getName().equals(player.team.getName()))
            {
                player.setTeam(null);
                return true;
            }
        }
        Lo4jDemo.writeError("Invalid operation - removeUser "+this.account.getUserName());
        throw new Exception("Invalid operation");
    }


    /**
     * gal
     * permission : add couch
     * @param couch
     * @return
     */
    public boolean addCouch(Coach couch) throws Exception
    {
        if(couch!=null && permissions.contains(permission.addCoach)&&team!=null && couch.team==null)
        {
            couch.setBoss(this);
            couch.setTeam(team);
            return true;
        }
        Lo4jDemo.writeError("Invalid operation - addCouch "+this.account.getUserName());
        throw new Exception("Invalid operation");
    }

    /**
     * permission : remove coach
     * @param coach
     * @return
     */
    public boolean removeCoach(Coach coach) throws Exception
    {
        if(coach!=null && permissions.contains(permission.removeCoach)&&team!=null)
        {
            if(coach.getBoss().account.getUserName().equals(this.account.getUserName()) && coach.team.getName().equals(team.getName()))
            {
                String[] key={"Key",this.account.getUserName(),coach.getAccount().getUserName()};
                apointmentsDaoSql.delete(key);
            }
            return true;
        }
        Lo4jDemo.writeError("Invalid operation - removeCouch "+this.account.getUserName());
        throw new Exception("Invalid operation");
    }

    /**
     * permission :add financial action
     * @param description
     * @param price
     * @return
     */

    public boolean addFinancialAction(String description, double price) throws Exception {
        if(permissions.contains(permission.addFinancial)&&team!=null)
        {
            FinancialAction financialAction = new FinancialAction(description,price,this);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String sDate=dateFormat.format(financialAction.getDate());
            system.addPaymentToPaymentSystem(this.team.getName(),sDate,price);
        }
        Lo4jDemo.writeError("Invalid operation - addCouch "+this.account.getUserName());
        throw new Exception("Invalid operation");
    }

    /**
     * permission: add assets to team
     * @param name
     * @return
     */

    public boolean addAssets(String name,String type) throws Exception {
        if(permissions.contains(permission.addAsset)&&team!=null)
        {
            if(type.equals("Field"))
            {
                String[]key={team.getName(),name,"Field"};
                assetsDauSql.save(key);
                return true;
            }
        }
        Lo4jDemo.writeError("Invalid operation - addAssets "+this.account.getUserName());
        throw new Exception("Invalid operation");
    }

    public void removeAsset(String name) throws Exception {
        if(permissions.contains(permission.removeAsset)&&team!=null)
        {
            String[]key={"key",team.getName(),name};
            assetsDauSql.delete(key);
        }
        Lo4jDemo.writeError("Invalid operation - removeAsses "+this.account.getUserName());
        throw new Exception("Invalid operation");
    }

    /**
     * permissions: update team page
     * @param message
     * @return
     */

    public boolean updateTeamPage (String message) throws Exception
    {
        if(permissions.contains(permission.updateTeamPage)&&team!=null)
        {
            team.uploadDataToPage(message);
            return true;
        }
        Lo4jDemo.writeError("Invalid operation - updateTeamPage "+this.account.getUserName());
        throw new Exception("Invalid operation");
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

}

