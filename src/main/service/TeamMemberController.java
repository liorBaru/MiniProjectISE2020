package main.service;

import main.domain.Asset.TeamMember;
import main.domain.manageUsers.Guest;

public class TeamMemberController extends GuestController
{
    TeamMember teamMember;

    public TeamMemberController(Guest guest) {
        super(guest);
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
        if (!message.isEmpty())
        {
            teamMember.uploadDataToPage(message);
        }
    }
}
