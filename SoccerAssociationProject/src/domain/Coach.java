package domain;

import java.util.Date;
import java.util.List;

public class Coach extends TeamMember
{

    private String training;
    private String job;

    public Coach (Account account, String name, Team team, Date contract , double salary, String training)
    {
        super(account,name,team,contract,salary);
        this.training=training;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Coach (Account account, String name)
    {
        super(account,name);
    }

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

    @Override
    public String getType() {
        return "Coach: "+this.name;
    }

}

