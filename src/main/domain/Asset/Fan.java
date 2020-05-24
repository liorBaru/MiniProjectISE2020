package main.domain.Asset;

import main.DB.FansDaoSql;
import main.DB.PageFollowersDaoSql;
import main.DB.PagesDaoSql;
import main.DB.System;
import main.Demo.Lo4jDemo;
import main.domain.manageEvents.Complaint;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.User;

import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

public class Fan extends User
{

    private static PageFollowersDaoSql pageFollowersDaoSql =PageFollowersDaoSql.getInstance();
    private static FansDaoSql fansDaoSql =FansDaoSql.getInstance();
    private static PagesDaoSql pagesDaoSql =PagesDaoSql.getInstance();


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
        System system= main.DB.System.getInstance();
        Account account = system.getAccountManager().getAccount(data[0]);
        List<String[]> fanData=fansDaoSql.get(data);
        if(fanData.isEmpty()==false)
        {
            Fan fan = new Fan(fanData.get(0),account);
            return fan;
        }
        Lo4jDemo.writeError("username not found"+data[0]);
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

    /**
     * watch search history
     * @return
     */

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
    public List<String> showPersonalDetails()
    {
        List<String> userDetails = super.showPersonalDetails();
        String pageString =" pages:";
        userDetails.add(pageString);
        for (String s:showPages().values())
        {
            pageString=pageString+", "+s;
        }
        userDetails.add(pageString);
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
