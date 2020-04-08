package domain;

import java.util.List;

public class IFA extends User

{
    private static List<League> leagues;
    private static List<Refree> refrees;




    public boolean createNewLeugue(String name, int level){return false;}
    public boolean addSeasonToLeague(String LeagueName, int year){return false;}
    public boolean addReferee( User user){return false;}
    public void addRefereeToLeague(String refree, String League){}
    public boolean removeRefree(String refree){return false;}
    public boolean updatePointsPolicy(LeagueCalcolator calcolator, String league, int season){return false;}
    public boolean updateGamePolicy(GameScheduale scheduale, String league, int year){return false;}
    public boolean startSeason(String league, int season){return false;}


}
