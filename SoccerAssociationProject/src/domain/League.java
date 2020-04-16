package domain;

import java.util.InputMismatchException;
import java.util.List;
import java.util.TreeMap;

public class League
{
    private String name;
    private int level;
    private List<Refree> refrees;
    private TreeMap<Season, SeasonInfo> seasons;

    public League(String name, int level) {
        this.name = name;
        this.level = level;

    }
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public TreeMap<Season, SeasonInfo> getSeasons() {
        return seasons;
    }

    /**
     * @author: David Zaltsman
     * @desc: add season to league
     * @param season - the league that we want to add her season
     */
    public League addSeasonToLeague(League league,Season season)
    {
        //TODO: we will update seasonInfo at the current  use case for policy season update
            if(season==null || seasons.get(season)!=null || !System.getInstance().getLeagues().contains(league))
                throw new InputMismatchException("Wrong inputs");
            SeasonInfo seasoninfo=new SeasonInfo(null,null);
            seasons.put(season,seasoninfo);
            return league;
    }


}
