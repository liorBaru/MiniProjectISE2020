package domain;

import java.util.Date;
import java.util.List;

public class Player extends TeamMember
{
    Date birthDay;
    List<PlayerPosition> positions;
    int goals;
    int games;

    public Player(Account account,String name,Team team ,Date birthDay,double salary)
    {
        super(account,name,team,birthDay,salary);
        this.page = new Page(this);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Player(Account account,String name,Date birthDay)
    {
        super(account,name);
        this.page = new Page(this);
        this.birthDay = birthDay;
    }

    @Override
    public void removeUser()
    {
        if(team!=null)
        {
            team.removeStaffMember(this);
            this.team=null;
        }
    }

    public void uploadDataToPage(String data){}

    public String getType(){
    return "Player: "+this.name;
    }
}

enum PlayerPosition
{
    GK, SW, CB, LB, RB, DM, CM, LM, RM, AM, SS, LW, RW ,CF
}
