package domain.Asset.Refree;

import main.DB.IntegrationTests;
import main.DB.System;
import main.domain.Asset.Refree.Referee;
import main.domain.manageEvents.Notification;
import main.domain.manageLeagues.IFA;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class RefereeTest {
    System system=System.getInstance();
    IFA ifa;

    @Test
    @Category({ IntegrationTests.class})
    public void removeUserTest1Integration() {


        try
        {
            Referee referee =   system.createNewRefereeUser("Ban","Ban123456","ban123","Line","training");
            ifa=system.createNewIFAUser("Dan","Dan123456","dan122");
            referee.removeUser();
        }
        catch (Exception e)
        {

        }
        boolean flag=false;
        for (Notification n:ifa.readNotification()) {
            flag=n.getDetails().equals( "Referee, Ban, has been remove from the system by the system manager");
        }
        assertTrue(flag);
    }
}