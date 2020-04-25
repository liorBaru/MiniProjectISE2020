package domain.Asset.Refree;

import DB.IntegrationTests;
import DB.RegressionTests;
import DB.System;
import domain.manageEvents.Notification;
import domain.manageLeagues.IFA;
import domain.manageUsers.Account;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class RefreeTest {
    System system=System.getInstance();
    IFA ifa=new IFA("Dan",new Account("dan122","Dan123456"));

    @Test
    @Category({ IntegrationTests.class})
    public void removeUserTest1Integration() {
        Refree refree=new LineRefree("Ban",new Account("ban123","Ban123456"));
        system.addIFA(ifa);
        refree.removeUser();
        boolean flag=false;
        for (Notification n:ifa.readNotification()) {
            flag=n.getDetails().equals( "Refree, Ban, has been remove from the system by the system manager");
        }
        assertTrue(flag);
    }
}