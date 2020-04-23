package domain;

import domain.manageUsers.Account;
import org.junit.Before;

import static org.junit.Assert.*;

public class AccountTest {

    Account account;

    @Before
    public void setUp(){
        account= new Account("userName","Pass123");
    }

    @org.junit.Test
    public void accountVerificationSuccess1Unit() {
        assertTrue(account.accountVerification("Pass123"));
    }

    @org.junit.Test
    public void accountVerificationFailure2Unit() {
        assertFalse(account.accountVerification("ERROR"));
    }

    @org.junit.Test
    public void accountVerificationFailure3Unit() {
        assertFalse(account.accountVerification(null));
    }

}