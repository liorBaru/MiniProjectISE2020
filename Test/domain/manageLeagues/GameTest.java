package domain.manageLeagues;

import DB.IntegrationTests;
import DB.UnitTests;
import domain.Asset.Fan;
import domain.manageUsers.Account;
import domain.manageUsers.AccountStub;
import domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private User user;

    public void setUpUnit(){
        game= new Game();
        user= new Fan("gameFan",new AccountStub());
    }

    public void setUpIntegration(){
        game= new Game();
        user= new Fan("gameFan", new Account("gameFan","passFan123"));
    }

    @Test
    @Category({UnitTests.class})
    public void addFollowerSuccess1Unit() throws Exception {
        setUpUnit();
        assertTrue(game.addFollower(user));
    }

    @Test
    @Category({IntegrationTests.class})
    public void addFollowerSuccess1Integration() throws Exception {
        setUpIntegration();
        assertTrue(game.addFollower(user));
    }

}