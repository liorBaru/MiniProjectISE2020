package domain;

import java.util.Date;

public class Complaint
{
    private User user ;
    private boolean status;
    private String details;
    private String answer;
    private Date createdDate;
    private Date answerDate;

    public Complaint (User user, String details)
    {
        this.user=user;
        this.details=details;
        status=true;
        createdDate=new Date();
    }

    public boolean getStatus() {
        return status;
    }

    public void setAnswer (String answer)
    {
        this.answer=answer;
        status=false;
        answerDate=new Date();
    }
    public String getDetails()
    {
        return this.details;
    }

    public String getAnswer()
    {
        return answer;
    }

    public User getUser(){return this.user;}
}
