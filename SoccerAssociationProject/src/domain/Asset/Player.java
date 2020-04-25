package domain.Asset;

import domain.manageUsers.Account;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Player extends TeamMember
{
    private Date birthDay;
    private List<PlayerPosition> positions;
    private int goals;
    private int games;
    private int yellowCards;
    private int redCards;

    public Player(Account account, String name, Date birthDay)
    {
        super(account,name);
        this.birthDay=birthDay;
        positions= new LinkedList<>();
        goals=0;
        games=0;
        yellowCards=0;
        redCards=0;
    }
    /**
     * show player personal details
     * @return
     */
    @Override
    public List<String> showPersonalDetails()
    {
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

    public String getType()
    {
    return "Player: "+this.name;
    }
}

enum PlayerPosition
{
    GK, SW, CB, LB, RB, DM, CM, LM, RM, AM, SS, LW, RW ,CF
}
