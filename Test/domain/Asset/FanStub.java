package domain.Asset;

import main.domain.Asset.Fan;
import main.domain.manageUsers.Account;

public class FanStub extends Fan {

    public FanStub() {
        super("fan",new Account("fanUser","passFAN123"));
    }
}
