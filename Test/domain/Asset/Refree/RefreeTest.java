package domain.Asset.Refree;

import main.DB.IntegrationTests;
import main.DB.System;
import main.domain.Asset.Refree.Refree;
import main.domain.manageEvents.Notification;
import main.domain.manageLeagues.IFA;
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