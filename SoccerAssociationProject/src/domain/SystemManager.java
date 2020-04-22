package domain;

import java.util.List;

public class SystemManager extends User
{



    public List<Complaint> watchComplaints()
    {
        return system.getComplaints();
    }


    /**
     * remove user from the system
     * @param userName
     * @return
     * @throws Exception
     */
    public boolean removeUserFromSystem (String userName) throws Exception
    {
        return system.removeUser(userName);

    }

    public void removeUser()
    {
        //
    }

    public SystemManager (String userName,Account account)
    {
        super(userName,account);
    }

    public boolean closeTeam (String teamName)
    {
        return system.closeTeamBySystemManager(teamName);
    }

    /**
     * gal
     * answer to fan complaint
     * @param complaint
     * @param answer
     */
    public void answerComplaint (Complaint complaint, String answer )
    {
        if(complaint!=null && answer.isEmpty()==false)
        {
            system.ansComplaint(complaint,answer);
        }
    }






}
