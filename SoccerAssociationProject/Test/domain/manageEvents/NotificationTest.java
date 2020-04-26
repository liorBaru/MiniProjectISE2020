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
        Notification n1=new Notification("Team Close");
        Notification n2=new Notification("Team Open");
        assertEquals(n1.compareTo(n2),0);
    }

    @Test(expected = NullPointerException.class)
    @Category({UnitTests.class})
    public void compareToNullInput() {
        Notification n1=new Notification("Team Close");
        n1.compareTo(null);
    }
}