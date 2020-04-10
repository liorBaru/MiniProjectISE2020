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

    public Player(String userName, String password, String name, String job, Team team, int contract, double salary, Page page, Date birthDay, List<PlayerPosition> positions, double salary1, int goals, int games) {
        super(userName, password, name, job, team, contract, salary);
        this.page = page;
        this.birthDay = birthDay;
        this.positions = positions;
        this.salary = salary1;
        this.goals = goals;
        this.games = games;
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
