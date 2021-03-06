package domain.Asset;

import domain.manageTeams.Team;
import domain.manageUsers.AccountStub;

import java.sql.SQLException;

public class CoachStub extends Coach {
    public CoachStub() throws SQLException {
        super(new AccountStub(), "account","training");
    }

    @Override
    public void setTeam(Team team)
    {
        this.team = team;
    }
}
