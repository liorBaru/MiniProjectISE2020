package domain.Asset;

import domain.manageUsers.Account;

import java.util.List;

public class Coach extends TeamMember
{

    private String training;
    private String job;

    public Coach (Account account, String name,String training)
    {
        super(account,name);
        this.training=training;
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

