package service;

import domain.TeamMember;

public class TeamMemberController
{
    TeamMember teamMember;


    public void upLoadDataToPage(String message)
    {
        if (!message.isEmpty())
        {
            teamMember.uploadDataToPage(message);
        }
    }
}
