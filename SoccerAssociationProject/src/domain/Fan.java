package domain;

import java.util.LinkedList;
import java.util.List;

public class Fan extends User
{
    private List<String> searchHistory;
    private List<Page> pages;


    public Fan(String name, Account account)
    {
        super(name,account);
        searchHistory=new LinkedList<>();
        pages=new LinkedList<>();
    }


    /**
     * remove page from pages and user from pages followers
     * @param pageID
     * @return
     * @throws Exception
     */
    public boolean unfollowPage(int pageID) throws Exception {
        for (Page page:pages)
        {
            if(page.getPageID()==pageID)
            {
                pages.remove(page);
                return system.unFollowPage(page,this);
            }
        }
        throw new Exception(this.name +" is not following the chosen page");
    }

    /**
     * gal
     * add page to pages and user to page followers
     * @param pageID
     * @return
     * @throws Exception
     */

    public boolean followPage(int pageID) throws Exception {

       return system.followPage(pageID,this);
    }

    public void addPage(Page page)
    {
        if(pages.contains(page))
        {
            return;
        }
        pages.add(page);
    }

    public boolean followGame(Game game) throws Exception {
        if (game!=null)
        {
            return game.addFollower(this);
        }
        return false;
    }

    public List<String> watchHistory()
    {
        return searchHistory;
    }

    public List<Page> showPages()
    {
        return pages;
    }

    public List<Page> findPage(String page)
    {
        return system.findPage(page);
    }


    /**
     * gal
     * writing complaint to system manager
     * @param deatails
     */
    public void fillingComplaint(String deatails)
    {
        if(deatails.isEmpty())
        {
            return ;
        }
        Complaint complaint = new Complaint(this,deatails);
        system.addComplaint(complaint);
    }

    @Override
    public void removeUser() throws Exception {
        for (Page page:pages)
        {
            page.removeFollower(this);
        }
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
        for (Page page :pages)
        {
            userDetails.add(page.getPageName());
        }
        return userDetails;
    }
}
