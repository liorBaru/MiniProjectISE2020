package domain;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;


public class System
{
    private static System system;
    private List<League> leagues;
    private List<Team> teams;
    private List<Season> seasons;

    public List<Season> getSeasons() {
        return seasons;
    }

    private List<Asset> assetsExists;

    public List<League> getLeagues() {
        return leagues;
    }

    private List<SystemManager> systemManagers;


    public static void initSystem(String userName, String password, String name)
    {
        SystemManager systemManager = new SystemManager(new Account(userName,password),name);
        system=getInstance();
        system.systemManagers.add(systemManager);
        //TODO:
        // Using try\catch to announce the user if some problem occurs.
        // 1.connect to DB
        // 2.connect to accounting IFA
        // 3.connect to tax law

    }


    private System ()
    {
        leagues = new LinkedList<>();
        teams = new LinkedList<>();
        systemManagers= new LinkedList<>();
        seasons=new LinkedList<>();
    }

    public static System getInstance()
    {
    if(system!=null)
    {
        return system;
    }
    else
    {
        return system=new System();
    }
}

    /**
     * @author: David Zaltsman
     * @desc: add new league to system. USECASE 9.1 -> if wrong deatials return appropriate message.
     * @param name - name of league
     * @param level - level of the league
     */
    public League addLeague(String name, int level)
    {
        if(level<0 || checkLeagueExist(level) ){
            throw new InputMismatchException("Wrong input");
        }
        League league=new League(name,level);
        this.leagues.add(league);
        return league;
    }

    /**
     * @author: David Zaltsman
     * @desc: add new Season to system. USECASE 9.2.1 -> if wrong deatials return appropriate message.
     * @param year- year of the season
     * @param start- if start ture so we cant modify and changes at seasoninfo
     */
    public Season addSeason(int year, Boolean start)
    {
        if(year<1995 ){
            throw new InputMismatchException("Wrong input");
        }
        Season season=new Season(year,start);
        this.seasons.add(season);
        return season;
    }

    /**
     * @author: David Zaltsman
     * @desc: private function => check if the league is already exists
     * @param level- level of leage
     */
    //check if the league is alreay exits
    private boolean checkLeagueExist(int level) {
        for (League league: this.leagues)
        {
            if(league.getLevel()==level)
                return true;
        }
        return false;
    }
}
