package domain.Asset;



import DataAccess.PageMessagesDaoSql;
import DataAccess.RegressionTests;
import DataAccess.UnitTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.junit.Assert.*;

public class BoardMemberTest
{
    private BoardMember boardMember;
    private Player player ;
    private Coach coach;

    @Before
    public void setUp()
    {
        try
        {
            String [] ownerS ={"Key","ownerTest"};
            String [] playerS={"Key","playerTest"};
            String [] coachS={"Key","coachTest"};
            boardMember=Owner.getOwnerFromDB(ownerS);
            player=Player.getPlayerFromDB(playerS);
            coach=Coach.getCoachFromDB(coachS);
        }
        catch (Exception e)
        {

        }
    }

    @Test
    @Category({UnitTests.class,RegressionTests.class})
    public void removePlayer()
    {
        try
        {
            boardMember.removePlayer(player);
            assertTrue(player.getTeam()==null);
            boardMember.addPlayer(player);
            assertEquals(player.getTeam().getName(),boardMember.team.getName());
            assertFalse(boardMember.getAppointments().isEmpty());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void removeCoach()
    {
        try
        {
            boardMember.removeCoach(coach);
            assertTrue(coach.getTeam()==null);
            boardMember.addCouch(coach);
            assertEquals(coach.getTeam().getName(),boardMember.team.getName());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    @Category(UnitTests.class)
    public void addAssets()
    {
        try
        {
            boardMember.addAssets("assetName","Field");
            assertFalse(boardMember.team.getAssetsOfTeam().isEmpty());
            boardMember.removeAsset("assetName");
            assertTrue(boardMember.team.getAssetsOfTeam().isEmpty());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    @Category(UnitTests.class)
    public void updateTeamPage ()
    {
        try
        {
            boardMember.updateTeamPage("new message from"+boardMember.getName());
            PageMessagesDaoSql pageMessage=PageMessagesDaoSql.getInstance();
            String [] key={String.valueOf(boardMember.getTeam().getPage().getPageID())};
            String [] key2={"Page",String.valueOf(boardMember.getTeam().getPage().getPageID())};
            assertFalse(pageMessage.get(key).isEmpty());
            pageMessage.delete(key2);
            assertTrue(pageMessage.get(key).isEmpty());
        }
        catch (Exception e)
        {

        }
    }


}