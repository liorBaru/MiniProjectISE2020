package domain;

import java.util.List;

public class SystemManager extends User
{
    private static List<Complaint> complaints;
    private static List<Team> closedTeams;





    public SystemManager (Account account, String name)
    {
        super(name,account);
    }
    public void answerComplaint (Complaint complaint, String answer ){}
    public List<Complaint> watchComplaints(){return  null;}
    public boolean removeUser (User user){return false;}
    public boolean closeTeam (Team team){return false;}
    public void watchInformation(){};

    @Override
    public String showPersonalDetails() {
       int a;
        return null;
    }

    @Override
    public void updateDetailes() {

    }


}
