package domain.manageUsers;

import main.DB.UnitTests;
import main.domain.manageUsers.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class AccountTest {

    Account account;

    @Before
    public void setUp(){
        account= new Account("userName","Pass123");
    }

    @Test
    @Category({UnitTests.class})
    public void accountVerificationSuccess1Unit() {
        assertTrue(account.accountVerification("Pass123"));
    }

    @Test
    @Category({UnitTests.class})
    public void accountVerificationFailure2Unit() {
        assertFalse(account.accountVerification("ERROR"));
    }

    @Test
    @Category({UnitTests.class})
    public void accountVerificationFailure3Unit() {
        assertFalse(account.accountVerification(null));
    }

}