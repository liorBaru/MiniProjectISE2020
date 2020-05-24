package main.domain.manageTeams;

import main.DB.*;
import main.DB.System;
import main.domain.Asset.*;
import main.domain.manageEvents.Notification;
import main.domain.manageLeagues.Game;
import main.domain.manageLeagues.Season;
import main.domain.managePages.Page;
import main.domain.managePages.pageable;
import main.domain.manageUsers.User;

import java.sql.SQLException;
import java.util.*;

public class Team implements pageable
{
    private String name;
    private Page page;
    private boolean status;
   // private TreeMap<Integer, Game> games;
   // private TreeMap<Season, TeamInfo> seasons;
  //  private Set<FinancialAction> financialActions;

    private static TeamDaoSql teamDaoSql;
    private static StaffMembersDaoSql staffMembersDaoSql;
    private static OwnersDaoSql ownersDaoSql;
    private static CoachDaoSql coachDaoSql;
    private static PlayerDaoSql playerDaoSql;
    private static TeamManagerDaoSql teamManagerDaoSql;
    private static AssetsDauSql assetsDauSql;

    public Team (List<Owner> owners, String name) throws Exception
    {
        if (owners == null || owners.isEmpty()) {
            throw new Exception("cant open a team without a owner");
        }
        this.name = name;
        try {
            page = new Page(this);
        } catch (Exception e) {
            throw new Exception("failed to create a page for the team, "+e.getMessage());
        }
        status = true;
        String[] params = new String[3];
        params[0]=name;
        params[1]=String.valueOf(page.getPageID());
        params[2]=String.valueOf(status);
        teamDaoSql.save(params);
    }

    public Team (String name, Page page,boolean teamStatus)
    {
        this.name=name;
        this.page=page;
        this.status=teamStatus;
    }

    public static Team createTeamFromDB(String teamName) throws Exception
    {
        String[] key={teamName};
        List<String[]> teams = teamDaoSql.get(key);
        if(teams==null || teams.isEmpty())
        {
            throw new Exception("cant find team");
        }
        String[] teamDetails=teams.get(0);
        Page page =Page.createPageFromDB(teamDetails[1]);
        boolean teamStatus=Boolean.parseBoolean(teamDetails[2]);
        Team team = new Team(teamName,page,teamStatus);
        return team;
    }

    @Override
    public String getPageOwnerName()
    {
        return name;
    }

    public Page getPage() {
        return page;
    }

    public boolean isStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public List<Owner> getOwners()
    {
        List<Owner> owners=new LinkedList<>();
        try
        {
            for (String [] ownerString :ownersDaoSql.getAll())
            {
                if(ownerString[2]!=null && ownerString[2].equals(this.name))
                {
                    Owner owner = Owner.createOwnerFromDB(ownerString);
                    owners.add(owner);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return owners;
    }

    public List<Player> getPlayers()
    {
        String [] key ={"Team",name};
        List<Player> players =new LinkedList<>();
        try
        {
            for (String [] playerString:playerDaoSql.get(key))
            {
                Player player=Player.createPlayerFromDB(playerString);
                players.add(player);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return players;
    }

    public List<Coach> getCoach()
    {
        String [] key ={"Team",name};
        List<Coach> coaches=new LinkedList<>();
        try
        {
            for (String [] coachString: coachDaoSql.get(key))
            {
                Coach coach =Coach.createCoachFromDB(coachString);
                coaches.add(coach);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return coaches;
    }

    public List<TeamManager> getTeamManager()
    {
        String [] key ={"Team",name};
        List<TeamManager> teamManagers= new LinkedList<>();
        try
        {
            for (String[] teamMemberString:teamManagerDaoSql.get(key))
            {
                TeamManager teamManager=TeamManager.createTeamManagerFromDB(teamMemberString);
                teamManagers.add(teamManager);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return teamManagers;
    }


    public List<StaffMember> getStaffMembers()
    {
        List<StaffMember> staffMembers= new LinkedList<>();
        staffMembers.addAll(getOwners());
        staffMembers.addAll(getTeamManager());
        staffMembers.addAll(getCoach());
        staffMembers.addAll(getPlayers());
        return staffMembers;
    }

    public void setStatus(boolean status) throws Exception {
        if (this.status != status)
        {
            this.status = status;
            String statusString = status == false ? " is inactive" : " is active";
            Notification notification = new Notification("The Team: " + name + statusString);
            for (StaffMember staffMember : getStaffMembers())
            {
                System system=System.getInstance();
                system.sendNotification(staffMember.getAccount().getUserName(),notification);
            }
            //todo acknowledge the system and archive the history of the team
        }
        else if(status==false && this.status==false){
            throw new Exception("The team is not active already ");
        }
        else {
            throw new Exception("The team is active already ");
        }
    }

    public void addStaffMember(StaffMember member) throws SQLException {
        if (member != null)
        {
            member.setTeam(this);
            String[] key={member.getAccount().getUserName(),member.getType()};
            staffMembersDaoSql.save(key);
        }
    }

    public void setClose(Notification notification) throws SQLException
    {
        this.status = false;
        String[] key={"Team",name};
        for (String [] staffMember : staffMembersDaoSql.get(key))
        {
            if(staffMember[1].equals("Owner") || staffMember[1].equals("TeamManager"))
            {
                System system= System.getInstance();
                system.sendNotification(staffMember[0],notification);
            }
        }
    }

    public void removeTeamManger(TeamManager teamManager) throws SQLException {
        if(teamManager.team.getName().equals(name))
        {
            String[] key={teamManager.getAccount().getUserName()};
            staffMembersDaoSql.delete(key);
        }
        else
        {
            throw new IllegalArgumentException("The TeamManger is not in this team");
        }
    }

    public void removeStaffMember(StaffMember member) throws Exception
    {
        if (member != null)
        {
            member.removeTeam(this);
        }
    }

    public ArrayList<Asset> getAssetsOfTeam()
    {
        ArrayList<Asset> assetsOfTeam =new ArrayList<>();
        String [] key={"team",this.getName()} ;
        List<String[]> assets=assetsDauSql.get(key);
        for (String[] asset:assets)
        {
            if(asset[1].equals("Field"))
            {
                Field field =new Field(asset[0],this);
                assetsOfTeam.add(field);
            }
        }
        return assetsOfTeam;
    }

    public void uploadDataToPage(String data) throws Exception
    {
        if (data.isEmpty() == false) {
            page.addDataToPage(data);
        }
    }


}
