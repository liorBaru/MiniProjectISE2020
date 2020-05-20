package main.domain.Asset;



import main.DB.PlayerDaoSql;
import main.DB.System;
import main.domain.managePages.Page;
import main.domain.manageTeams.Team;
import main.domain.manageUsers.Account;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private static PlayerDaoSql playerDaoSql;


    public Player(Account account, String name, Date birthDay, List<String> positionsSt) throws SQLException
    {
        super(account,name);
        this.birthDay=birthDay;
        positions= new LinkedList<>();
        setPositions(positionsSt);
        goals=0;
        games=0;
        yellowCards=0;
        redCards=0;
        String [] params= new String[10];
        params[0]=account.getUserName();
        params[1]="";
        params[2]=name;
        params[3]=String.valueOf(this.page.getPageID());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        params[4]=dateFormat.format(birthDay);
        for (String positon:positionsSt)
        {
            params[5]=positon+", ";
        }
        if(params[5].length()>0)
        {
            params[5]=params[5].substring(0,params[5].length()-2);
        }
        params[6]=String.valueOf(goals);
        params[7]=String.valueOf(redCards);
        params[8]=String.valueOf(yellowCards);
        params[9]=String.valueOf(games);
        playerDaoSql.save(params);
    }

    public Player (Account account, Team team, String name, Page page, Date birthDay, String[] positions, int goals,int redCards,int yellowCards,int games)
    {
        this.account=account;
        this.team=team;
        this.name=name;
        this.page=page;
        this.birthDay=birthDay;
        this.goals=goals;
        this.redCards=redCards;
        this.yellowCards =yellowCards;
        this.games=games;
        transferToPlayerPositions(positions);
    }

    private void transferToPlayerPositions(String[] positions)
    {
        this.positions=new LinkedList<>();
        for (String position:positions)
        {
            PlayerPosition playerPosition=PlayerPosition.valueOf(position);
            this.positions.add(playerPosition);
        }
    }

    public static Player getPlayerFromDB(String[] key) throws Exception {
        List<String[]> players=playerDaoSql.get(key);
        if(players==null || players.isEmpty())
        {
            throw new Exception("wrong username");
        }
        String[] player=players.get(0);
        return createPlayerFromDB(player);
    }

    public static Player createPlayerFromDB(String[] player) throws Exception
    {
        System system = System.getInstance();
        Account account =system.getAccountManager().getAccount(player[0]);
        Team team =null;
        if(player[1]!=null && player[1].isEmpty()==false)
        {
            team=Team.createTeamFromDB(player[1]);
        }
        Page page=Page.createPageFromDB(player[3]);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate=null;
        if(player[4]!=null && player[4].isEmpty()==false)
        {
            birthDate =dateFormat.parse(player[4]);
        }
        String [] positions=player[5].split(",");
        int goals=Integer.valueOf(6);
        int redC=Integer.valueOf(7);
        int yellowC=Integer.valueOf(player[8]);
        int games=Integer.valueOf(player[9]);
        Player player1 = new Player(account,team,player[2],page,birthDate,positions,goals,redC,yellowC,games);
        return player1;
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

    @Override
    protected void update()
    {
        String [] params= new String[10];
        params[0]=account.getUserName();
        params[1]="";
        params[2]=name;
        params[3]=String.valueOf(this.page.getPageID());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        params[4]=dateFormat.format(birthDay);
        for (PlayerPosition positon:this.positions)
        {
            params[5]=positon.name()+", ";
        }
        if(params[5].length()>0)
        {
            params[5]=params[5].substring(0,params[5].length()-2);
        }
        params[6]=String.valueOf(goals);
        params[7]=String.valueOf(redCards);
        params[8]=String.valueOf(yellowCards);
        params[9]=String.valueOf(games);
        playerDaoSql.update(params);
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay)
    {
        this.birthDay = birthDay;
        update();
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
        update();
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
        update();
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
        update();
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
        update();
    }

    public List<PlayerPosition> getPositions() {
        return positions;
    }

    private void setPositions(List<String> positionsSt)
    {
        for (String position:positionsSt)
        {
           PlayerPosition positionToAdd = PlayerPosition.valueOf(PlayerPosition.class, position );
            if(positionToAdd!=null)
            {
                this.positions.add(positionToAdd);
            }
        }
        update();
    }

    public String getType()
    {
        return "Player";
    }
}

enum PlayerPosition
{
    GK, SW, CB, LB, RB, DM, CM, LM, RM, AM, SS, LW, RW ,CF
}
