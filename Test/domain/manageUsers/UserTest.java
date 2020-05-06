package domain.manageUsers;

import main.DB.IntegrationTests;
import main.DB.UnitTests;
import main.domain.Asset.Fan;
import domain.Asset.FanStub;
import main.domain.manageUsers.Account;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UserTest {

    @Test
    @Category({UnitTests.class})
    public void updatePassword1Unit(){
        FanStub fanStub= new FanStub();
        try {
            fanStub.updatePassword("passFAN123","passFAN1234");
            fanStub.getAccount().accountVerification("passFAN1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Category({IntegrationTests.class})
    public void updatePassword1Integration(){
        Fan fan= new Fan("name", new Account("userName","passFAN123"));
        try {
            fan.updatePassword("passFAN123","passFAN1234");
            fan.getAccount().accountVerification("passFAN1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}