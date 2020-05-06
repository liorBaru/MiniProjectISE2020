package domain.Asset;

import main.domain.Asset.Owner;
import main.domain.manageUsers.Account;

public class OwnerStub extends Owner {

    public OwnerStub() {
        super(new Account("Owner1","passOwner123"),"owner1");

    }


}
