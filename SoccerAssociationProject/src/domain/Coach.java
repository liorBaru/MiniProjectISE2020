package domain;

import java.util.Date;

public class Coach extends TeamMember
{

    private String training;
    private String job;
    Date birthDay;

    public Coach (Account account, String name, Team team, Date contract , double salary, String training, Date birthDay)
    {
        super(account,name,team,contract,salary);
        this.training=training;
        this.birthDay = birthDay;
    }
    public Coach (Account cAccount, String cName, Date birthDay)
    {
        super(cAccount,cName);
        this.birthDay = birthDay;
    }
    public void uploadDataToPage(String data){}



    @Override
    public String getType() {
        return "Coach: "+this.name;
    }
}

