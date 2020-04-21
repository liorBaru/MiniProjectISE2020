package domain;

import java.util.Date;
import java.util.List;

public class Player extends TeamMember
{
    private Date birthDay;
    private List<PlayerPosition> positions;
    private int goals;
    private int games;

    public Player(Account account,String name,Team team ,Date birthDay,double salary)
    {
        super(account,name,team,birthDay,salary);
        this.page = new Page(this);
    }


    @Override
    public List<String> showPersonalDetails() {
       List<String> userDetails= super.showPersonalDetails();
       String birth ="BirthDay:"+birthDay.toString();
       String positionsS="positions: ";
       for (PlayerPosition position:positions)
       {
           positionsS+=position+" ";
       }
       String goalSt="Goals: "+goals;
       String gameSt ="Games: "+games;
       userDetails.add(birth);
       userDetails.add(positionsS);
       userDetails.add(goalSt);
        userDetails.add(gameSt);
        return userDetails;
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
