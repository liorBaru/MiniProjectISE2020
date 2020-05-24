package domain.manageUsers;

import DataAccess.UnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    @Category({UnitTests.class})
    public void accountVerificationSuccess1Unit()
    {
        Account account=new Account("fanTest","Galb1234");
        assertTrue(account.accountVerification("Galb1234"));
    }

    @Test
    @Category({UnitTests.class})
    public void accountVerificationFailure2Unit()
    {
        Account account=new Account("fanTest","Galb1234");
        assertFalse(account.accountVerification("ERROR"));
    }

    @Test
    @Category({UnitTests.class})
    public void accountVerificationFailure3Unit()
    {
        Account account=new Account("fanTest","Galb1234");
        assertFalse(account.accountVerification(null));
    }

}