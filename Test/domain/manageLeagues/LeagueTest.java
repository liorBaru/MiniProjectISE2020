package domain.manageLeagues;

import main.DB.IntegrationTests;
import main.DB.RegressionTests;
import main.DB.UnitTests;
import main.domain.manageLeagues.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class LeagueTest {

    private League league;
    private Season season;
    private  SeasonStub seasonStub;

    private void setUpUnit(){
        league= new League("liga",1);
        seasonStub= new SeasonStub();
    }

    private void setUpIntegration(){
        league= new League("liga",1);
        season= new Season(2020);
    }


    @Test (expected = Exception.class)
    @Category({UnitTests.class})
    public void addSeasonToLeague1Unit()
    {
        setUpUnit();
        //league.addSeasonToLeague(null);
    }


    @Test
    @Category({UnitTests.class})
    public void addSeasonToLeagueSuccess2Unit()
    {
        setUpUnit();
        //league.addSeasonToLeague(seasonStub);
        HashMap<Season, SeasonInfo> HM1= league.getSeasonInfos();
        SeasonInfo SI1= HM1.get(seasonStub);
        HashMap<League, SeasonInfo> HM2=seasonStub.getSeasonInfos();
        SeasonInfo SI2= HM2.get(league);
        assertEquals(SI1,SI2);
    }

    @Test
    @Category({IntegrationTests.class, RegressionTests.class})
    public void addSeasonToLeagueSuccess1Integration()
    {
        setUpIntegration();
        //league.addSeasonToLeague(season);
        HashMap<Season, SeasonInfo> HM1= league.getSeasonInfos();
        SeasonInfo SI1= HM1.get(season);
        HashMap<League, SeasonInfo> HM2=season.getSeasonInfos();
        SeasonInfo SI2= HM2.get(league);
        assertEquals(SI1,SI2);
    }

    @Test
    @Category({IntegrationTests.class})
    public void updatePolicyToLeagueSuccess2Integration()
    {
        setUpIntegration();
        //league.addSeasonToLeague(season);
        LeagueCalculator leagueCalculator= new LeaguePointsCalculator(20,20,20);
        league.updatePolicyToLeague(season,leagueCalculator);
        HashMap<Season, SeasonInfo> HM1= league.getSeasonInfos();
        SeasonInfo SI1= HM1.get(season);
        LeagueCalculator leagueCalculator2= SI1.getLeagueCalculator();
        assertEquals(leagueCalculator,leagueCalculator2);

    }


    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void updatePolicyToLeagueFailed4Integration()
    {
        setUpIntegration();
        league.addSeasonToLeague(null);
        LeagueCalculator leagueCalculator= new LeaguePointsCalculator(20,20,20);
        league.updatePolicyToLeague(null,leagueCalculator);

    }

    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void updatePolicyToLeagueFailed3Integration()
    {
        setUpIntegration();
        league.addSeasonToLeague(null);
        LeagueCalculator leagueCalculator= new LeaguePointsCalculator(20,20,20);
        league.updatePolicyToLeague(season,null);

    }


}