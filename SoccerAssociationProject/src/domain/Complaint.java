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
    }

    public void setAnswer (String answer)
    {
        this.answer=answer;
    }
    public String getDetails()
    {
        return this.details;
    }

    public String getAnswer()
    {
        return answer;
    }
}
