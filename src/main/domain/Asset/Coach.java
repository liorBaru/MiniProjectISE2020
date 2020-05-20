package main.domain.Asset;

import main.DB.CoachDaoSql;
import main.DB.FansDaoSql;
import main.DB.TeamDaoSql;
import main.domain.manageTeams.Team;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.User;

import java.util.ArrayList;
import java.util.List;

public class Coach extends TeamMember
{
    private String training;
    private String job;
    private static CoachDaoSql coachDaoSql;
    private static TeamDaoSql teamDaoSql;
    public Coach (Account account, String name, String training)
    {
        super(account,name);
        this.training=training;
    }

    public static User createCoach(String[] data) {
        Account account = new Account(data[0],data[1]);
        List<String[]> coachData=coachDaoSql.get(data);
        if(coachData.isEmpty()==false)
        {
            Coach coach = new Coach(account,data[5],data[4]);
            List<String[]> ownersData=teamDaoSql.getAll();
            List<Owner> owners = new ArrayList<>();
            for(String[] owner: ownersData)
            {
                Owner owner = new Owner()
            }
            List<Owner> getAllTeamOwners =
            Team coachTeam = new Team(,data[1]);
            coach.setTeam(data[1]);
            return fan;
        }
        throw new Exception("username not found");
    }

    /**
     * gal
     * get personal details
     * @return
     */

    @Override
    public List<String> showPersonalDetails()
    {
        List<String> userDetails= super.showPersonalDetails();
        String sJob = "Job: "+job;
        String sTrining ="Training: "+training;
        userDetails.add(sTrining);
        userDetails.add(sJob);
        return userDetails;
    }

    /**
     * get type and name
     * @return
     */
    @Override
    public String getType() {
        return "Coach: "+this.name;
    }

    public void setJob(String job)
    {
        this.job=job;
    }
}

