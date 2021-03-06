package domain.Asset;

import DataAccess.*;
import DataAccess.System;
import domain.manageEvents.Complaint;
import domain.manageUsers.Account;
import domain.manageUsers.User;
import Logger.Lo4jDemo;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Fan extends User
{

    private static PageFollowersDaoSql pageFollowersDaoSql =PageFollowersDaoSql.getInstance();
    private static FansDaoSql fansDaoSql =FansDaoSql.getInstance();
    private static PagesDaoSql pagesDaoSql =PagesDaoSql.getInstance();
    private static GameFollwersDaoSql    gameFollowersDaoSql = GameFollwersDaoSql.getInstance();


    public Fan(String name, Account account) throws SQLException {
        super(name,account);
        String [] key={account.getUserName(),name};
        fansDaoSql.save(key);
    }

    public Fan(String [] data,Account account)
    {
        super(data[0],account);
        name=data[0];
        this.account=account;
    }

    public static Fan createFanFromDB(String[]data) throws Exception {
        System system= System.getInstance();
        Account account = system.getAccountManager().getAccount(data[0]);
        List<String[]> fanData=fansDaoSql.get(data);
        if(fanData.size()!=0)
        {
            String[] newData={account.getUserName(),"Gal"};
            Fan fan = new Fan(newData,account);
            return fan;
        }
        throw new Exception("username not found");
    }

    /**
     * remove page from pages and user from pages followers
     * @param pageID
     * @return
     * @throws Exception
     */
    public boolean unfollowPage(int pageID) throws Exception
    {
        String [] params= new String[3];
        params[0]="key";
        params[1]=String.valueOf(pageID);
        params[2]=this.account.getUserName();
        pageFollowersDaoSql.delete(params);
        return true;
    }

    public TreeMap<Integer,String> getFollwingPages()
    {
        TreeMap<Integer,String > pagesMap= new TreeMap<>();
        String [] key ={"user_name",this.account.getUserName()};
        List<String[]> pages= pageFollowersDaoSql.get(key);
        for (String [] pageData:pages)
        {
           pagesMap.put(Integer.valueOf(pageData[0]),"");
        }
        TreeMap<Integer,String > allPages=showPages();
        for (int pageID:allPages.keySet())
        {
            if(pagesMap.containsKey(pageID))
            {
                pagesMap.replace(pageID,allPages.get(pageID));
            }
        }
        return getFollwingPages();
    }

    /**
     * gal
     * add page to pages and user to page followers
     * @param pageID
     * @return
     * @throws Exception
     */
    public boolean followPage(int pageID) throws Exception
    {
        String [] params= new String[2];
        params[0]=String.valueOf(pageID);
        params[1]=this.account.getUserName();
        pageFollowersDaoSql.save(params);
        return true;
    }

    public boolean followGame(int gameID) throws Exception
    {
        String [] params= new String[2];
        params[0]=String.valueOf(gameID);
        params[1]=this.account.getUserName();
        gameFollowersDaoSql.save(params);
        return true;
    }
    /**
     * watch search history
     * @return
     */

    public boolean unfollowGame(int gameID) throws Exception
    {
        String [] params= new String[3];
        params[0]="key";
        params[1]=String.valueOf(gameID);
        params[2]=this.account.getUserName();
        gameFollowersDaoSql.delete(params);
        return true;
    }
    public List<String> watchHistory()
    {
        return null;
    }

    /**
     * gal,
     * get fan following pages
     * @return
     */
    public TreeMap<Integer, String> showPages()
    {
        TreeMap<Integer,String> pagesMap=new TreeMap<>();
        List<String []> allpages=pagesDaoSql.getAll();
        for (String[] allpage:allpages)
        {
            int id=Integer.valueOf(allpage[0]);
            pagesMap.put(id,allpage[1]);
        }
        return pagesMap;
    }



    /**
     * gal
     * writing complaint to system manager
     * @param deatails
     */
    public void fillingComplaint(String deatails) throws Exception
    {
        if(deatails==null || deatails.isEmpty())
        {
            Lo4jDemo.writeError("cant write empty complaint");
            throw new Exception("cant write empty complaint");
        }
        Complaint complaint = new Complaint(this,deatails);
    }

    /**
     * gal
     * return fan personal Details
     * @return
     */
    @Override
    public LinkedList<String> showPersonalDetails()
    {
        LinkedList<String> userDetails = super.showPersonalDetails();
        userDetails.addFirst("Fan");
        return userDetails;
    }

    @Override
    public boolean removeUser() throws Exception {
        return true;
    }

    @Override
    protected void update() throws SQLException {
        String[] params={account.getUserName(),name};
        fansDaoSql.update(params);
    }
}
