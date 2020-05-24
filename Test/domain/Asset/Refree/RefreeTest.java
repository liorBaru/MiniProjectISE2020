package domain.Asset.Refree;

import DataAccess.IntegrationTests;
import DataAccess.System;
import domain.Asset.SystemManager;
import domain.manageLeagues.IFA;
import domain.manageUsers.User;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class RefreeTest {

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
    public void getRefreeFromDB()
    {

    }

}