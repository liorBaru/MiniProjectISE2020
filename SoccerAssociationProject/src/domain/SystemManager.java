package domain;

import java.util.List;

public class SystemManager extends User
{
    private static List<Complaint> complaints;
    private static List<Team> closedTeames;






    public void answerComplaint (Complaint complaint, String answer ){}
    public List<Complaint> watchComplaints(){return  null;}
    public boolean removeUser (User user){return false;}
    public boolean closeTeam (Team team){return false;}
    public void watchInformation(){};

    @Override
    public String showPersonalDetails() {
        return null;
    }

    @Override
    public void updateDetailes() {

    }


}
