package domain.manageEvents;

import DataAccess.ComplaintDaoSql;
import DataAccess.System;
import domain.manageUsers.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    private static ComplaintDaoSql complaintDaoSql;


    public Complaint (User user, String details) throws SQLException {
        this.user=user;
        this.details=details;
        status=true;
        createdDate=new Date();
        ID++;
        complaintID=ID;
        String [] complaintString = new String[5];
        complaintString[0]=String.valueOf(complaintID);
        complaintString[1]=user.getAccount().getUserName();
        complaintString[2]=String.valueOf(status);
        complaintString[3]=details;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        complaintString[4]=simpleDateFormat.format(createdDate);
        complaintDaoSql.save(complaintString);
    }
    public Complaint (User user, int id, boolean status,String details,String answer, Date createdDate,Date answerDate)
    {
        this.user=user;
        this.status=status;
        this.complaintID=id;
        this.details=details;
        this.answer=answer;
        this.createdDate=createdDate;
        this.answerDate=answerDate;
    }

    public static Complaint createComplaintFromSql(String[] complaintString) throws Exception {
        int id=Integer.valueOf(complaintString[0]);
        System system=System.getInstance();
        User user = system.getAccountManager().getUser(complaintString[1],"Fan");
        boolean status=Boolean.valueOf(complaintString[2]);
        String details=complaintString[3];
        String answer=complaintString[4];
        Date date1=null;
        Date date2=null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(complaintString[5].isEmpty()==false)
        {
            date1=simpleDateFormat.parse(complaintString[5]);
        }
        if(complaintString[6].isEmpty()==false)
        {
            date2=simpleDateFormat.parse(complaintString[6]);
        }
        Complaint complaint = new Complaint(user,id,status,details,answer,date1,date2);
        return complaint;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public int getComplaintID()
    {
        return complaintID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setAnswer (String answer) throws Exception {
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
