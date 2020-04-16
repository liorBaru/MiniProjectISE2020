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
    }


    public boolean unfollowPage(Page page)
    {
        if(page!=null)
        {
            return page.removeFollwer(this);
        }
        return false;
    }

    public boolean followPage(Page page)
    {
        if(page!=null)
        {
           return page.addFollwer(this);
        }
        return false;
    }

    public boolean followGame(Game game)
    {
        if (game!=null)
        {
            return game.addFollwer(this);
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


    public boolean fillingComplaint(String deatails)
    {
        Complaint complaint = new Complaint(this,deatails);
        system.addComplaint(complaint);
        return true;
    }

    @Override
    public void removeUser()
    {
        for (Page page:pages)
        {
            page.removeFollwer(this);
        }
    }

    @Override
    public String showPersonalDetails()
    {
        return null;
    }



    @Override
    public void updateDetailes() {

    }
}
