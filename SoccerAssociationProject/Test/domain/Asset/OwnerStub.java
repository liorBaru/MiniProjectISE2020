package domain.Asset;

import domain.manageTeams.Team;
import domain.manageUsers.Account;

public class OwnerStub extends Owner{

    public OwnerStub() {
        super(new Account("Owner1","passOwner123"),"owner1");

    }


}
