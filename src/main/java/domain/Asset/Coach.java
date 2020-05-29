package domain.Asset;

import DataAccess.CoachDaoSql;
import DataAccess.System;
import domain.managePages.Page;
import domain.manageTeams.Team;
import domain.manageUsers.Account;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Coach extends TeamMember
{
    private static CoachDaoSql coachDaoSql=CoachDaoSql.getInstance();
    private String training;
    private String job;

    public Coach (Account account, String name, String training) throws SQLException
    {
        super(account,name);
        this.training=training;
        String [] params = new String[6];
        params[0]=account.getUserName();
        params[1]="";
        params[2]=String.valueOf(this.page.getPageID());
        params[3]=training;
        params[4]="";
        params[5]=name;
        job="";
        coachDaoSql.save(params);
    }

    public Coach(Account account, Team team, Page page, String training, String job, String name)
    {
        this.account=account;
        this.team=team;
        this.page=page;
        this.training=training;
        this.job=job;
        this.name=name;
    }

    public static Coach getCoachFromDB(String [] username) throws Exception {
        List<String[]> coachs=coachDaoSql.get(username);
        String[] coach=coachs.get(0);
        return createCoachFromDB(coach);
    }

    public static Coach createCoachFromDB(String [] coach) throws Exception {
        System system= System.getInstance();
        Account account=system.getAccountManager().getAccount(coach[0]);
        Team team=null;
        if(coach[1]!=null && coach[1].isEmpty()==false)
        {
            team=Team.createTeamFromDB(coach[1]);
        }
        Page page=Page.createPageFromDB(coach[2]);
        Coach coach1 = new Coach(account,team,page,coach[3],coach[4],coach[5]);
        page.setOwner(coach1);
        return coach1;
    }

    /**
     * gal
     * get personal details
     * @return
     */
    @Override
    public LinkedList<String> showPersonalDetails()
    {
        LinkedList <String> userDetails= super.showPersonalDetails();
        userDetails.addLast(training);
        userDetails.addLast(job);
        userDetails.addFirst("Coach");
        if(team!=null)
        {
           userDetails.addLast(team.getName());
        }
        else
        {
            userDetails.addLast("");
        }
        userDetails.addLast(page.getPageName());
        return userDetails;
    }


    /**
     * get type and name
     * @return
     */
    @Override
    public String getType() {
        return "Coach";
    }

    public void setJob(String job) throws SQLException {
        this.job=job;
        update();
    }

    public void setTraining(String training) throws SQLException {
        this.training=training;
        update();
    }

    @Override
    protected void update() throws SQLException {
        String [] params = new String[6];
        params[0]=account.getUserName();
        params[1]="";
        params[2]=String.valueOf(this.page.getPageID());
        params[3]=training;
        params[4]="";
        params[5]=name;
        coachDaoSql.update(params);
    }
}

