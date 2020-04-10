package domain;

import java.util.Date;
import java.util.List;

public class Player extends TeamMember implements pageable
{
    private Page page;
    Date birthDay;
    List<PlayerPosition> positions;
    double salary;
    int goals;
    int games;



    public void uploadDataToPage(String data){}
}

enum PlayerPosition
{
    GK, SW, CB, LB, RB, DM, CM, LM, RM, AM, SS, LW, RW ,CF
}
