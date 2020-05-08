import main.DB.AcceptanceTests;
import DB.SystemTest;
import domain.Asset.BoardMemberTest;
import domain.Asset.FanTest;
import domain.Asset.Refree.RefereeTest;
import domain.manageEvents.NotificationTest;
import domain.manageLeagues.GameTest;
import domain.manageLeagues.LeagueTest;
import domain.manageTeams.TeamTest;
import domain.manageUsers.AccountManagerTest;
import domain.manageUsers.AccountTest;
import domain.manageUsers.GuestTest;
import domain.manageUsers.UserTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import service.BoardManagerControllerTest;
import service.FanControllerTest;
import service.IFAControllerTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(AcceptanceTests.class)
@Suite.SuiteClasses({SystemTest.class,
        BoardManagerControllerTest.class, FanControllerTest.class,
        IFAControllerTest.class, BoardMemberTest.class,FanTest.class,
        RefereeTest.class, NotificationTest.class, GameTest.class,
        LeagueTest.class,TeamTest.class, AccountManagerTest.class,
        AccountTest.class, GuestTest.class,UserTest.class
})
public class AcceptanceTestSuite {
}