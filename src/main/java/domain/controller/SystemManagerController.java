package domain.controller;
import DataAccess.System;
import domain.Asset.SystemManager;
import domain.manageUsers.User;

public class SystemManagerController extends GuestController
{
    private SystemManager systemManager;

    public SystemManagerController(User user)
    {
        if(user.getKind().equals("SystemManager"))
        {
            this.systemManager=(SystemManager)user;
        }

    }

    public boolean initSystem(String userName, String password, String name) throws Exception {
        if(userName!=null && password!=null && name!=null) {
            if (userName.length() >= 6 && password.length() >= 8) {
                System.initSystem(userName, password, name);
            }
        }
        return false;
    }

    public boolean removeUser(String userName) throws Exception
    {
        return systemManager.removeUserFromSystem(userName);
    }

    public void getAllTeams()
    {
        systemManager.getTeams();
    }

    public void closeTeam(String teamName) throws Exception {
         systemManager.closeTeam(teamName);
    }

    /**
     * gal,
     * answer complaint
     * @param answer
     * @param complaintID
     * @throws Exception
     */
    public void answerComplaint(String answer, int complaintID) throws Exception {
        systemManager.answerComplaint(complaintID,answer);
    }

    /**
     * gal,
     * watch all new complaints
     */
    public void getNewComplaints () throws Exception
    {
        if(systemManager.watchComplaints().isEmpty())
        {
            throw new Exception("No new Complaints");
        }
    }






}
