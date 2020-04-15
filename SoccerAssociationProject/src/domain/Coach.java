package domain;

import java.util.Date;

public class Coach extends TeamMember
{

    private String training;
    private String job;

    public Coach (Account account, String name, Team team, Date contract , double salary, String training)
    {
        super(account,name,team,contract,salary);
        this.training=training;
    }

    public void uploadDataToPage(String data){}



    @Override
    public String getType() {
        return "Coach: "+this.name;
    }
}

