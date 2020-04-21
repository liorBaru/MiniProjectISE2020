package domain;

import java.util.List;

public class SystemManager extends User
{

    public SystemManager (String name,Account account)
    {
        super(name,account);
    }

    public List<Complaint> watchComplaints()
    {
        return system.getComplaints();
    }

    public boolean removeUserFromSystem (String userName) throws Exception {
        return system.removeUser(userName);
    }

    public void removeUser()
    {
        // throw exception
    }

    public SystemManager (String userName, String password, String name)
    {
        super(userName,password,name);
    }

    public boolean closeTeam (String teamName)
    {
        return system.closeTeamBySystemManager(teamName);
    }

    public void watchInformation(){};

    public void answerComplaint (Complaint complaint, String answer )
    {
        if(complaint!=null && answer.isEmpty()==false)
        {
            system.ansComplaint(complaint,answer);
        }
    }






}
