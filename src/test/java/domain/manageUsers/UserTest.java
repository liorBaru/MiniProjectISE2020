package domain.manageUsers;

import DataAccess.IntegrationTests;
import DataAccess.UnitTests;
import domain.Asset.Fan;
import domain.Asset.FanStub;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UserTest {

    @Test
    @Category({UnitTests.class})
    public void updatePassword1Unit(){

        try {
            FanStub fanStub= new FanStub();
            fanStub.updatePassword("passFAN123","passFAN1234");
            fanStub.getAccount().accountVerification("passFAN1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Category({IntegrationTests.class})
    public void updatePassword1Integration(){
        try {
            Fan fan= new Fan("name", new Account("userName","passFAN123"));
            fan.updatePassword("passFAN123","passFAN1234");
            fan.getAccount().accountVerification("passFAN1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}