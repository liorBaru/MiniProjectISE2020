package main.domain.manageLeagues;

import main.DB.LeaguesDaoSql;
import main.domain.Asset.Refree.Refree;

import java.sql.SQLException;
import java.util.*;

public class League
{
    private String name;
    private int level;
    private List<Refree> refrees;
    private HashMap<Season, SeasonInfo> seasonInfos;

    private static LeaguesDaoSql leaguesDaoSql;

    public League(String name, int level) throws SQLException {
        this.name = name;
        this.level = level;
        String[] key ={name,String.valueOf(level)};
        leaguesDaoSql.save(key);
        seasonInfos=new HashMap<>();
        refrees=new LinkedList<>();
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

}
