package domain.Asset;

import domain.manageTeams.Team;
import domain.manageUsers.Account;
import domain.manageUsers.AccountStub;

public class CoachStub extends Coach{
    public CoachStub() {
        super(new AccountStub(), "account");
    }

    @Override
    public void setTeam(Team team)
    {
        this.team = team;
    }
}
