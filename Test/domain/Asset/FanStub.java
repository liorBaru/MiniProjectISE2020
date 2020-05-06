package domain.Asset;

import domain.manageUsers.Account;

public class FanStub extends Fan{

    public FanStub() {
        super("fan",new Account("fanUser","passFAN123"));
    }
}
