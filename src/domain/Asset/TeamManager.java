package domain.Asset;

import DataAccess.System;
import DataAccess.TeamManagerDaoSql;
import domain.manageTeams.Team;
import domain.manageUsers.Account;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TeamManager extends BoardMember
{
    private static TeamManagerDaoSql teamManagerDaoSql;

    public TeamManager(Account account, String name, Team team, BoardMember boss, List<String> permissions) throws SQLException
    {
        super(account,name,team,boss,"TeamManager");
        String []key={account.getUserName(),team.getName(),name};
        teamManagerDaoSql.save(key);
        setPermissions(permissions);
    }

    public TeamManager(Account account,Team team, String name) throws Exception {
        this.account=account;
        this.team=team;
        this.name=name;
        String [] params={"Employee",account.getUserName()};
        List<String[]> bossList=apointmentsDaoSql.get(params);
        if(bossList!=null && bossList.isEmpty()==false)
        {
            String[] bossS=bossList.get(0);
            String type = system.getAccountManager().getUserType(bossS[0]);
            if(type.equals("Owner"))
            {
                this.boss =Owner.getOwnerFromDB(bossS);
            }
            else if(type.equals("TeamManager"))
            {
                this.boss =TeamManager.getTeamManagerFromDB(bossS);
            }
        }
        String[] queryParams={"user_name",account.getUserName()};
        List<String[]> permissions =permissionsDaoSql.get(queryParams);
        this.permissions =new LinkedList<>();
        for (String[] permissionString :permissions)
        {
            permission per = permission.valueOf(permissionString[0]);
            this.permissions.add(per);
        }
    }

    public static TeamManager getTeamManagerFromDB(String[] key) throws Exception {
        List<String[]> teamManager=teamManagerDaoSql.get(key);
        if(teamManager==null || teamManager.isEmpty())
        {
            throw new Exception(" worng arguments");
        }
        String [] details=teamManager.get(0);
        return createTeamManagerFromDB(details);
    }

    public static TeamManager createTeamManagerFromDB(String [] details) throws Exception {

        System system = System.getInstance();
        Account account =system.getAccountManager().getAccount(details[0]);
        Team team =null;
        if(details[1]!=null && details[1].isEmpty()==false)
        {
            team=Team.createTeamFromDB(details[1]);
        }
        TeamManager teamManager1 = new TeamManager(account,team,details[2]);
        return teamManager1;
    }

    @Override
    public String getType() {
        return "TeamManager";
    }

    public void setPermissions(List<String>permissions) throws SQLException {
        if (permissions != null)
        {
            for (String permission : permissions)
            {
                String [] params = {account.getUserName(),permission};
                permissionsDaoSql.save(params);
            }
        }
    }

    public void cleanPermission() throws SQLException {
        String [] key = {"user_name",this.account.getUserName()};
        permissionsDaoSql.delete(key);
    }


    @Override
    protected void update() throws SQLException {
        String teamName="";
        if(this.team!=null)
        {
            teamName=this.team.getName();
        }
        String [] params = {account.getUserName(),teamName,name};
        teamManagerDaoSql.update(params);
    }


}
