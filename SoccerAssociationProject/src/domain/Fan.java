package domain;

import java.util.List;

public class Fan extends User
{
    private List<String> searchHistory;
    private List<Page> pages;


    public Fan(String name, Account account)
    {
        super(name,account);
    }


    public boolean unfollowPage(Page page){return false;}
    public boolean followPage(Page page){return false;}
    public boolean followGame(Game game) {return false;}
    public boolean fillingComplaint(String deatails){return false;}
    public List<String> watchHistory(){return null;}
    public List<Page> findNewPages(){return null;}
    public List<Page> findPage(String page){return null;}



    @Override
    public String showPersonalDetails() {
        return null;
    }

    @Override
    public void updateDetailes() {

    }
}
