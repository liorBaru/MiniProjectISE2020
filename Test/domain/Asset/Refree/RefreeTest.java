package domain.Asset.Refree;

import main.DB.IntegrationTests;
import main.DB.System;
import main.domain.Asset.Refree.Refree;
import main.domain.Asset.SystemManager;
import main.domain.manageEvents.Notification;
import main.domain.manageLeagues.IFA;
import main.domain.manageUsers.User;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class RefreeTest {
    private Refree refree;

    @Test
    @Category({ IntegrationTests.class})
    public void removeRefree()
    {
        try {
            System system=System.getInstance();
            User user=system.getAccountManager().getUser("SMTest","SystemManager");
            SystemManager systemManager=(SystemManager)user;
            assertTrue(systemManager.removeUserFromSystem("RefreeTest"));
            user=system.getAccountManager().getUser("IFATest","IFA");
            IFA ifa=(IFA)user;
            ifa.addReferee("name","Galb1234","RefreeTest","type","training");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }

}