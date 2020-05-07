package main.domain.Asset;

import main.domain.manageTeams.Team;
import main.domain.manageUsers.Account;
import main.domain.manageEvents.Complaint;
import main.domain.manageUsers.User;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class SystemManager extends User
{
    /**
     * gal,
     * get all new complaints from the system
     * @return
     */
    public TreeMap<Integer,String> watchComplaints()
    {
        TreeMap<Integer,String> complaints = new TreeMap<>();
        for (Complaint c :system.getNewComplaints())
        {
            int id=c.getComplaintID();
            String details=c.getDetails();
            complaints.put(id,details);
        }
        return complaints;
    }

    public List<String> getTeams()
    {
        List<String> teamNames= new LinkedList<>();
        for (Team team:system.getTeams())
        {
            teamNames.add(team.getName());
        }
        return teamNames;
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

    public SystemManager (String name, Account account)
    {
        super(name,account);
    }

    public void closeTeam (String teamName) throws Exception {
        system.closeTeamBySystemManager(teamName);
    }

    /**
     * gal
     * answer Complaint by system manager
     * @param complaintID
     * @param answer
     * @throws Exception
     */
    public void answerComplaint (int complaintID, String answer ) throws Exception
    {
        if(answer.isEmpty()==false)
        {
            system.ansComplaint(complaintID,answer);
        }
    }






}
