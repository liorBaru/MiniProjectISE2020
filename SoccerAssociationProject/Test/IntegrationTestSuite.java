import DB.AcceptanceTests;
import DB.IntegrationTests;
import DB.SystemTest;
import domain.Asset.BoardMemberTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import service.BoardManagerControllerTest;
import service.FanControllerTest;
import service.IFAControllerTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(IntegrationTests.class)
@Suite.SuiteClasses({SystemTest.class, BoardMemberTest.class,
        BoardManagerControllerTest.class, FanControllerTest.class,
        IFAControllerTest.class})
public class IntegrationTestSuite {
}