package domain;


import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public abstract class BoardMember extends StaffMember
{
    protected List<StaffMember> appointments;
    protected TreeMap<premission,Boolean> permissions;
    System system=System.getInstance();
    public BoardMember (Account account, String name, Team team, BoardMember boss)
    {
        super(account,name,team,boss);
        appointments = new LinkedList<>();
        permissions= new TreeMap<>();
    }



    public BoardMember(Account account, String name, Team team)
    {
      super(account,name,team);
    }





    public boolean addPlayer(String playerName)
    {
        if(permissions.get("addPlayer")==true)
        {
            Player player =system.getPlayer(playerName);
            if(player!=null)
            {

            }
            else
            {

            }
            player.setTeam(team);
            team.addAsset(player);
            team.addStaffMember(player);
            return true;
        }
        return false;
    }

    public boolean removePlayer(Player player)
    {
        if(player!=null && permissions.get("removePlayer")==true)
        {
            team.removeAsset(player);
            team.removeStaffMember(player);
            player.setTeam(null);
            return true;
        }
        return false;
    }

    public boolean addCouch(Coach couch)
    {
        if(couch!=null && permissions.get("addCoach")==true)
        {
            team.addStaffMember(couch);
            team.addAsset(couch);
            couch.setTeam(team);
            appointments.add(couch);
            return true;
        }
        return false;
    }

    public boolean removeCoach(Coach coach)
    {
        if(coach!=null && permissions.get("removeCoach")==true)
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

    public boolean addFinancialAction(String description, double price)
    {
        if(permissions.get("addFinancial")==true)
        {
            FinancialAction financialAction = new FinancialAction(description,price,this);
            return team.addFinancialAction(financialAction);
        }
        return false;
    }

    public boolean addAssets(String name)
    {
        if(permissions.get("addAsset")==true)
        {
            Asset field = system.addField(name,team);
            team.addAsset(field);
            return true;
        }
        return false;
    }

    public boolean updateTeamPage (String message)
    {
        if(permissions.get("updateTeamPage")==true)
        {
            team.uploadDataToPage(message);
            return true;
        }
        return false;
    }

    @Override
    public void removeTeam(Team team) {
        for (StaffMember appoint:appointments) {
            appoint.removeTeam(team);
        }
        this.getTeam().removeAsset(this);
        setTeam(null);
    }

}

enum premission{
    addPlayer, removePlayer, addCoach, removeCoach, addFinancial, addAsset ,updateTeamPage
}
