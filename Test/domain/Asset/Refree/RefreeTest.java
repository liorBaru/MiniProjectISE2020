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
    IFA ifa;

    @Test
    @Category({ IntegrationTests.class})
    public void removeUserTest1Integration() {


        try
        {
            Refree refree=   system.createNewRefereeUser("Ban","Ban123456","ban123","Line","training");
            ifa=system.createNewIFAUser("Dan","Dan123456","dan122");
            refree.removeUser();
        }
        catch (Exception e)
        {

        }
        boolean flag=false;
        for (Notification n:ifa.readNotification()) {
            flag=n.getDetails().equals( "Refree, Ban, has been remove from the system by the system manager");
        }
        assertTrue(flag);
    }
}