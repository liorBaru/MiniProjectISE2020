package domain.manageLeagues;

import java.util.HashMap;
import java.util.InputMismatchException;

public class Season
{
    private int year;
    private boolean start;
    private HashMap<League, SeasonInfo> seasoninfos;

    public Season(int year, boolean start) {
        this.year = year;
        this.start = start;
        seasoninfos =new HashMap<>();
    }


    /**
     * @author: David Zaltsman
     * @desc: add seasonInfo to league
     * @param league - the league that we want to add her seasonInfo
     * @param seasonInfo - the seasonInfo that we want to add
     */
    public Season setSeasonInfo(League league , SeasonInfo seasonInfo)
    {
        if(league==null || !seasoninfos.containsKey(league) || seasonInfo==null)
            throw new InputMismatchException("Wrong inputs");
        seasoninfos.put(league,seasonInfo);
        return this;
    }

}
