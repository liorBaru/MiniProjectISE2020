package main.domain.Asset;

import main.DB.ComplaintDaoSql;
import main.DB.FansDaoSql;
import main.DB.PageFollowersDaoSql;
import main.DB.PagesDaoSql;
import main.domain.manageEvents.Complaint;
import main.domain.managePages.Page;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class Fan extends User
{

    private static PageFollowersDaoSql pageFollowersDaoSql;
    private static FansDaoSql fansDaoSql;
    private static ComplaintDaoSql complaintDaoSql;
    private static PagesDaoSql pagesDaoSql;


    public Fan(String name, Account account)
    {
        super(name,account);
    }

    public static Fan createFanFromDB(String[]data) throws Exception {
        Account account = new Account(data[0],data[1]);
        List<String[]> fanData=fansDaoSql.get(data);
        if(fanData.isEmpty()==false)
        {
            Fan fan = new Fan(fanData.get(0)[1],account);
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
    public List<String> showPages()
    {
        String[] params = new String[3];
        params[0]="user_name";
        params[1]=this.account.getUserName();
        List<String[]> pagesFollows =pageFollowersDaoSql.get(params);
        if(pagesFollows.isEmpty()==false)
        {
            List<String> pages = new LinkedList<>();
            for (String [] pageDetailes:pagesFollows)
            {

                for (String [] pageName: pagesDaoSql.get(pageDetailes))
                {
                    pages.add(pageName[1]);
                }
            }
            return pages;
        }
        return null;
    }



    /**
     * gal
     * writing complaint to system manager
     * @param deatails
     */
    public void fillingComplaint(String deatails) throws SQLException {
        if(deatails.isEmpty())
        {
            return ;
        }
        Complaint complaint = new Complaint(this,deatails);
        String [] complaintString = new String[5];
        complaintString[0]=String.valueOf(complaint.getComplaintID());
        complaintString[1]=complaint.getUser().getAccount().getUserName();
        complaintString[2]=String.valueOf(complaint.getStatus());
        complaintString[3]=complaint.getDetails();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        complaintString[4]=simpleDateFormat.format(complaint.getCreatedDate());
        complaintDaoSql.save(complaintString);
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
        userDetails.addAll(showPages());
        return userDetails;
    }

    @Override
    public boolean removeUser() throws Exception {
        return true;
    }

    @Override
    protected void update()
    {
        String[] params={account.getUserName(),name};
        fansDaoSql.update(params);
    }
}
