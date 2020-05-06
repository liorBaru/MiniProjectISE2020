package main.domain.manageEvents;

import main.domain.manageUsers.User;

import java.util.Date;

public class Complaint
{
    private User user ;
    private boolean status;
    private String details;
    private String answer;
    private Date createdDate;
    private Date answerDate;
    private int  complaintID;
    private static int ID;


    public Complaint (User user, String details)
    {
        this.user=user;
        this.details=details;
        status=true;
        createdDate=new Date();
        ID++;
        complaintID=ID;
    }

    public int getComplaintID()
    {
        return complaintID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setAnswer (String answer)
    {
        this.answer=answer;
        status=false;
        answerDate=new Date();
        Notification notification=new Notification("System answer: "+answer);
        user.addNotification(notification);
    }
    public String getAnswer()
    {
        return answer;
    }
    public String getDetails()
    {
        return this.details;
    }



    public User getUser(){return this.user;}
}
