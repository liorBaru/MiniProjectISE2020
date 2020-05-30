package gui;

import Logger.NotificationSystem;
import domain.service.GuestController;
import domain.service.IFAController;
import domain.service.RefreeController;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GuestApplication implements Observer
{
    private TreeMap<String,GuestController> users;
    public GuestApplication()
    {
        users=new TreeMap<>();
    }

    /** str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message / "respond" ->type -"IFA"
     *  str[2]= "respond"-> username
     *  str[3]= "respond"-> name
     *  str[4]= "respond"-> "message1"
     *  str[5]= "respond"-> "message2"
     * @param
     * @param pass
     * @return
     */
    public String[] login (String username, String pass){
        GuestController controller=new GuestController();
        String[] ans;
        ans = controller.login(username,pass,this);
//        ans[0]="respond";
//        ans[1]="Referee";
//        ans[3]="Chen The Queen";
        return ans;

    }



    /** str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @param username
     * @param pass
     * @return
     */
    //TODO: after double pass check (get from gal)

    public String[] register(String name, String username, String pass)
    {
        GuestController controller=new GuestController();
        String[] ans=new String[6];
        ans= controller.register(name,username,pass);
        return ans;
    }



    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    //TODO: after double pass check (get from gal)
    public String[] addTeam (String teamName,String ownerUserName,String userName)
    {
        if(users.containsKey(userName))
        {
            IFAController ifa=(IFAController)users.get(userName);
            return ifa.addTeam(teamName,ownerUserName);
        }
        return null;
    }

    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    //TODO: after double pass check (get from gal)
    public String[] addOwner(String name,String username, String pass,String ifaUserName)
    {
        if(users.containsKey(ifaUserName))
        {
            IFAController ifaController=(IFAController)users.get(ifaUserName);
            return ifaController.addOwner(name,username,pass);
        }
        return null;
    }


    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message / "respond"-> "policy1"
     *  str[2]= "respond"-> "policy2"
     * @return
     */
    public String[] getCalculatorPolicy(String userName)
    {
       if(users.containsKey(userName))
       {
           IFAController ifaController=(IFAController)users.get(userName);
           return ifaController.getCalculatorPolicy();
       }
       return null;
    }

    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    public String[] setLegueCalculator(String leagueName, int leagueYear,String policy,String userName)
    {

        if(users.containsKey(userName))
        {
            IFAController ifaController = (IFAController) users.get(userName);
            return ifaController.setLeagueCalculator(leagueName, leagueYear, policy);
        }
        return null;
    }


    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message / "respond"-> "policy1"
     *  str[2]= "respond"-> "policy2"
     * @return
     */
    public String[] getGamesScedualsPolicy(String userName)
    {
        if(users.containsKey(userName))
        {
            IFAController ifaController = (IFAController) users.get(userName);
            return ifaController.getGamesScedualsPolicy();
        }
        return null;
    }

    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message / "respond"-> "policy1"
     *  str[2]= "respond"-> "policy2"
     * @return
     */
    public String[] setGamesSceduals(String leagueName, int leagueYear,String policy ,String userName)
    {
        if(users.containsKey(userName))
        {
            IFAController ifaController = (IFAController) users.get(userName);
            return ifaController.setGamesScedualsPolicy(leagueName,leagueYear,policy);
        }
        return null;
    }


    /** mainReferee!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    public String[] addedEvent(String playerUserName, int gameID, String event, int minute, Date date,String userName)
    {
        if(users.containsKey(userName))
        {
            RefreeController refreeController=(RefreeController)users.get(userName);
            return refreeController.addEvent(playerUserName,gameID,event,minute,date);
        }
        return null;
    }

    /** mainReferee!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    public String[] createReport(int gameID,String userName)
    {
        if(users.containsKey(userName))
        {
            RefreeController refreeController=(RefreeController)users.get(userName);
            return refreeController.createReport(gameID);
        }
        return null;
    }




    public ArrayList<String> getMsg(String userName)
    {
        ArrayList<String> msgList=new ArrayList<>();
        for (int i=0;i<10;i++){
            msgList.add(userName+" "+i);
        }
        return msgList;
    }


    @Override
    public void update(Observable obs, Object arg)
    {
        System.out.println("update application");
        if (obs instanceof GuestController)
        {
           if(arg!=null)
           {
               Object [] args=(Object[])arg;
               if(args.length>1)
               {
                   String userName=(String)args[0];
                   GuestController controller=(GuestController)args[1];
                   if(users.containsKey(userName)==false)
                   {
                       users.put(userName,controller);
                   }
               }
           }
        }
        else if(obs instanceof NotificationSystem)
        {
            String userName=(String)arg ;
            if(users.containsKey(userName))
            {
                // update gui with message to user
            }
        }
    }
}
