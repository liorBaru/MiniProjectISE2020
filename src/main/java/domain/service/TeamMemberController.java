package domain.service;

import domain.Asset.TeamMember;
import domain.manageUsers.Guest;
import domain.manageUsers.User;

public class TeamMemberController extends GuestController
{
    private TeamMember teamMember;

    public TeamMemberController(User user)
    {
        if(user.getKind().equals("Coach") || user.getKind().equals("Player"))
        {
            this.teamMember=(TeamMember)user;
        }
    }

    public void setUser(TeamMember teamMember) {
        this.teamMember = teamMember;
    }
//    public TeamMemberController(TeamMember teamMember)
//    {
//        this.teamMember=teamMember;
//    }


    /**
     * gal
     * upload data to user page
     * @param message
     */
    public void upLoadDataToPage(String message)
    {
        try
        {
            teamMember.uploadDataToPage(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }


    }
}
