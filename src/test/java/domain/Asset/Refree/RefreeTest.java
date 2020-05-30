package domain.Asset.Refree;

import DataAccess.GameEventsDaoSql;
import DataAccess.GamesDaoSql;
import DataAccess.IntegrationTests;
import DataAccess.System;
import domain.Asset.SystemManager;
import domain.manageLeagues.IFA;
import domain.manageUsers.User;
import javafx.beans.binding.StringBinding;
import org.junit.Test;
import org.junit.experimental.categories.Category;


import javax.rmi.CORBA.Util;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class RefreeTest {

    @Test
    @Category({ IntegrationTests.class})
    public void removeRefree()
    {
        try {
            System system=System.getInstance();
            User user=system.getAccountManager().getUser("refreeTest","Referee");
            SystemManager systemManager=(SystemManager)user;
            assertTrue(systemManager.removeUserFromSystem("RefreeTest"));
            user=system.getAccountManager().getUser("IFATest","IFA");
            IFA ifa=(IFA)user;
            ifa.addReferee("name","Galb1234","refreeTest","type","training");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    @Category({ IntegrationTests.class})
    public void addEvent()
    {
        try
        {
            System system =System.getInstance();
            MainRefree refree =(MainRefree) system.getAccountManager().getUser("refreeTest","Referee");
            String teamMemberUserName ="playerTest";
            int gameID=0;
            String event="Goal";
            int minute=16;
            Date date=new Date(Calendar.getInstance().getTimeInMillis());
            refree.addEvent(teamMemberUserName,gameID,event,minute,date);
            GameEventsDaoSql gameEventsDaoSql=GameEventsDaoSql.getInstance();
            String []key={String.valueOf(gameID)};
            assertTrue(gameEventsDaoSql.get(key).isEmpty()==false);
        }
        catch (Exception e)
        {

        }

    }

    @Test
    @Category({ IntegrationTests.class})
    public void createReport()
    {
        try
        {
            System system =System.getInstance();
            MainRefree refree =(MainRefree) system.getAccountManager().getUser("refreeTest","Referee");
            int gameID=0;
            refree.creareReport(gameID);
            GamesDaoSql gamesDaoSql=GamesDaoSql.getInstance();
            String[]key={"Key","0"};
            List<String[]> games= gamesDaoSql.get(key);
            String [] game=games.get(0);
            assertEquals("team2Test: 0 teamTest: 1",game[5]);
        }
        catch (Exception e)
        {

        }
    }



}