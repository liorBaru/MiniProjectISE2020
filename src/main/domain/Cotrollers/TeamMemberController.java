package main.domain.Cotrollers;

import main.domain.Asset.TeamMember;
import main.domain.manageUsers.Guest;
import main.service.GuestApplication;

public class TeamMemberController
{
    TeamMember teamMember;

    public void setUser(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

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
