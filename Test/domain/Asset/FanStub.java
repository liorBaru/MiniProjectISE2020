package domain.Asset;

import main.domain.Asset.Fan;
import main.domain.manageUsers.Account;

import java.sql.SQLException;

public class FanStub extends Fan {

    public FanStub() throws SQLException {
        super("fan",new Account("fanUser","passFAN123"));
    }
}
