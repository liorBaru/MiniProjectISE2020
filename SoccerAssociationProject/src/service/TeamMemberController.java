package service;

import domain.TeamMember;

public class TeamMemberController
{
    TeamMember teamMember;


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
