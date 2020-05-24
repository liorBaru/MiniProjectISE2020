package domain.service;
import domain.manageUsers.Guest;
import domain.manageLeagues.IFA;
import domain.manageLeagues.LeagueCalculator;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;


public class IFAController extends GuestController
{


    private IFA Ifa;

    public IFAController(Guest guest) {
        super(guest);
    }

    /**
     * @author: chen arazi
     * @desc: setIfa
     */
    public void setIfa(IFA ifa) {
        Ifa = ifa;
    }

    /**
     * @author: David Zaltsman
     * @desc: add new league
     * @param name
     * @param level
     */
    public void newLeague(String name, int level) {
        try
        {
            Ifa.addLeague(name, level);
        }
        catch (Exception e)
        {
            throw new InputMismatchException("Wrong input");
        }
    }

    /**
     * @author: David Zaltsman
     * @desc: add season to league
     * @param league - the league that we want to add her season
     * @param season - the season that we want to add
     */
    public void addSeasonToLeague(String league,int season)
    {
        //TODO:
        // 1.add to U.C 9.2.2 the functionality of adding seasonInfo
        try {
            Ifa.addSeasonToLeague(league, season);
        } catch (Exception e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    public String[]setLeagueCalculator (String league, int season, LeagueCalculator leaguePolicy)
    {
        String[] message=new String[2];
        try
        {
            Ifa.updatePolicyToLeague(league, season,leaguePolicy);
            message[0]="Respond";
            message[1]="Success operation";
        }
        catch (Exception e)
        {
            message[0]="Fail";
            message[1]=e.getMessage();
        }
        return message;
    }
    /**
         * @author: Lior Baruchovich
     * @desc:
            * @param
     * @param
     */
    public void addPlayer(String pName, Date birthDay, String password, String userName, List<String> positions) throws Exception{
        if( pName!=null && birthDay!=null && password!=null && userName!=null && positions!=null && positions.isEmpty()==false)
        {
            Ifa.addPlayer(pName,birthDay,password, userName,positions);
        }
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addCoach(String cName,String password, String userName, String training) throws Exception{
        if(cName!=null && password!=null && userName!=null && training!=null)
        {
            Ifa.addCoach(cName,password, userName,training);
        }
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public boolean addReferee( String rName,String password, String userName, String type,String training) throws Exception{
        if( rName!=null  && password!=null && userName!=null && type!=null)
        {
            Ifa.addReferee(rName,password, userName, type,training);
        }
        return false;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addIFA( String ifaName, String password, String userName) throws Exception{
        if( ifaName!=null && password!=null && userName!=null)
        {
            Ifa.addNewIFA( ifaName, password, userName);
        }
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public String[] addOwner(String OwnerName,String userName,String password)
    {
        String[] message=null;
        try
        {
            if( OwnerName!=null && password!=null && userName!=null)
            {
                Ifa.addOwner(OwnerName, password, userName);
                message = new String[2];
                message[0]="Respond";
                message[1]="Success operation";
            }
        }
        catch (Exception e)
        {
            message= new String[2];
            message[0]="Fail";
            message[1]=e.getMessage();
        }
        return message;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addTeam(String teamName, String ownerUserName)
    {
        String [] message=null;
        try
        {
            if( teamName!=null && teamName.isEmpty()==false  && ownerUserName!=null && ownerUserName.isEmpty()==false)
            {
                Ifa.addTeam( ownerUserName, teamName);
                message = new String[2];
                message[0]="Respond";
                message[1]="Success operation";
            }
            else
            {
                throw new Exception("Invalid arguments");
            }
        }
        catch (Exception e)
        {
            message = new String[2];
            message[0]="Fail";
            message[1]=e.getMessage();
        }

    }




}
