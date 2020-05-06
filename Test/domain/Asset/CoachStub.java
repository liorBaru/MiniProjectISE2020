package domain.Asset;

import main.domain.Asset.Coach;
import main.domain.manageTeams.Team;
import domain.manageUsers.AccountStub;

public class CoachStub extends Coach {
    public CoachStub() {
        super(new AccountStub(), "account","training");
    }

    @Override
    public void setTeam(Team team)
    {
        this.team = team;
    }
}
