package main.service;

import main.domain.Asset.TeamMember;

public class TeamMemberController
{
    TeamMember teamMember;
    public TeamMemberController(TeamMember teamMember)
    {
        this.teamMember=teamMember;
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
