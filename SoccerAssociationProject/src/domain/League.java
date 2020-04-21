package domain;

import java.util.*;

public class League
{
    private String name;
    private int level;
    private List<Refree> refrees;
    private HashMap<Season, SeasonInfo> seasonInfos;

    public League(String name, int level) {
        this.name = name;
        this.level = level;
        seasonInfos=new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public HashMap<Season, SeasonInfo> getSeasonInfos() {
        return seasonInfos;
    }

    /**
     * @author: David Zaltsman
     * @desc: add season to league
     * @param season - the league that we want to add her season
     */
    public League addSeasonToLeague(League league,Season season)
    {
        //TODO: we will update seasonInfo at the current  use case for policy season update
        if(season==null || !System.getInstance().getLeagues().contains(league))
            throw new InputMismatchException("Wrong inputs");
        SeasonInfo seasoninfo=new SeasonInfo(null,null);
        seasonInfos.put(season,seasoninfo);
        return league;
    }
    /**
     * @author: David Zaltsman
     * @desc: add Policyto to season
     * @param league - the league that we want to add her season
     * @param season - the season that we want to add her policy
     * @param leaguePolicy - an interface that hold the policy of league
     */
    public Season updatePolicyToLeague(League league,Season season, LeagueCalcolator leaguePolicy)
    {
        if(league==null||!System.getInstance().getLeagues().contains(league)||season==null||!seasonInfos.containsKey(season)|| leaguePolicy==null)
            throw new InputMismatchException("Wrong inputs");
        SeasonInfo seasonInfo= seasonInfos.get(season);
        seasonInfo.setLeagueCalcolator(leaguePolicy);
        return season;
    }

}
