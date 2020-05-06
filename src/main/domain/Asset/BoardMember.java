package main.domain.Asset;


import main.domain.manageUsers.Account;
import main.domain.manageTeams.FinancialAction;
import main.domain.manageTeams.Team;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public abstract class BoardMember extends StaffMember
{
    protected List<StaffMember> appointments;
    public TreeMap<permission,Boolean> permissions;

    public BoardMember (Account account, String name, Team team, BoardMember boss)
    {
        super(account,name,team,boss);
        appointments = new LinkedList<>();
        permissions= new TreeMap<>();
    }

    public BoardMember(Account account, String name, Team team)
    {
        super(account,name,team);
        appointments = new LinkedList<>();
        permissions= new TreeMap<>();
    }

    /**
     * gal
     * permission : remove player
     * @param player
     * @return
     */
    public boolean removePlayer(Player player)
    {
        if(player!=null && permissions.get(permission.removePlayer) && team!=null)
        {
            team.removeAsset(player);
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
    public boolean addCouch(Coach couch)
    {
        if(couch!=null && permissions.get(permission.addCoach)&&team!=null)
        {
            team.addStaffMember(couch);
            team.addAsset(couch);
            couch.setTeam(team);
            appointments.add(couch);
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
        if(coach!=null && permissions.get(permission.removeCoach)&&team!=null)
        {
            if(appointments.contains(coach))
            {
                appointments.remove(coach);
                team.removeStaffMember(coach);
                team.removeAsset(coach);
                coach.setTeam(null);
                return true;
            }
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
        if(permissions.get(permission.addFinancial)&&team!=null)
        {
            FinancialAction financialAction = new FinancialAction(description,price,this);
            return team.addFinancialAction(financialAction);
        }
        return false;
    }

    /**
     * permission: add assets to team
     * @param name
     * @return
     */

    public boolean addAssets(String name)
    {
        if(permissions.get(permission.addAsset)&&team!=null)
        {
            Asset field = system.addField(name,team);
            team.addAsset(field);
            return true;
        }
        return false;
    }

    /**
     * permissions: update team page
     * @param message
     * @return
     */

    public boolean updateTeamPage (String message)
    {
        if(permissions.get(permission.updateTeamPage)&&team!=null)
        {
            team.uploadDataToPage(message);
            return true;
        }
        return false;
    }

    @Override
    public void removeTeam(Team team) throws Exception {
        if(team!=null)
        {
            for (StaffMember appoint:appointments)
            {
                appoint.removeTeam(team);
            }
            if(this.team!=null)
            {
                this.team.removeAsset(this);
                this.team.removeStaffMember(this);
                this.team=null;
            }
        }
    }

    public void cleanPermission()
    {
        if(permissions!=null)
            permissions.clear();
    }



}

