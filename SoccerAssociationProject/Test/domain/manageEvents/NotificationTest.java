package domain.manageEvents;

import DB.IntegrationTests;
import DB.UnitTests;
import domain.manageEvents.Notification;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Date;

import static org.junit.Assert.*;

public class NotificationTest {

    @Test
    @Category({UnitTests.class})
    public void compareToWork() {
        Notification n1=new Notification("Team Close",new Date(2005,12,22));
        Notification n2=new Notification("Team Open",new Date(2012,12,22));
        assertEquals(n1.compareTo(n2),1);
    }

    @Test(expected = NullPointerException.class)
    @Category({UnitTests.class})
    public void compareToNullInput() {
        Notification n1=new Notification("Team Close",new Date(2005,12,22));
        n1.compareTo(null);
    }
}