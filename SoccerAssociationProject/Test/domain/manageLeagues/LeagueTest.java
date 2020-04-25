package domain.manageLeagues;

import DB.System;
import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class LeagueTest {

    private  League league;
    private  Season season;

    private void setUpUnit(){
        league= new League("liga",1);
        season= new Season(2020);
    }

    @Test
    public void addSeasonToLeagueFailed1Unit()
    {
        setUpUnit();
        league.addSeasonToLeague(season);
    }

    @Test
    public void addSeasonToLeagueSuccess2Unit()
    {
        setUpUnit();
        league.addSeasonToLeague(season);
        assertEquals(league.getSeasonInfos().get(season),season);
        //season.setSeasonInfo()
    }

}