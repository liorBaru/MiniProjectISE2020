package domain.Asset;



import DataAccess.PlayerDaoSql;
import DataAccess.System;
import domain.managePages.Page;
import domain.manageTeams.Team;
import domain.manageUsers.Account;

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

    private static PlayerDaoSql playerDaoSql=PlayerDaoSql.getInstance();


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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    public Player (Account account, Team team, String name, Page page, Date birthDay, String[] positions, int goals, int redCards, int yellowCards, int games)
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

    public static Player getPlayerFromDB(String[] key) throws Exception
    {
        String[]params={"Key",key[0]};
        List<String[]> players=playerDaoSql.get(params);
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate=null;
        if(player[4]!=null && player[4].isEmpty()==false)
        {
            birthDate =dateFormat.parse(player[4]);
        }
        String [] positions=player[5].split(",");
        int goals=Integer.valueOf(player[6]);
        int redC=Integer.valueOf( player[7]);
        int yellowC=Integer.valueOf(player[8]);
        int games=Integer.valueOf(player[9]);
        Player player1 = new Player(account,team,player[2],page,birthDate,positions,goals,redC,yellowC,games);
        page.setOwner(player1);
        return player1;
    }
    /**
     * show player personal details
     * @return
     */
    @Override
    public LinkedList<String> showPersonalDetails()
    {
       LinkedList<String> userDetails= super.showPersonalDetails();
       userDetails.addFirst("Player");
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       String birth =dateFormat.format(birthDay);
       userDetails.addLast(birth);
       String positionsS="";
       for (PlayerPosition position:positions)
       {
           positionsS+=position+" ";
       }
       userDetails.addLast(positionsS);
       String goalSt=String.valueOf(goals);
       String gameSt =String.valueOf(games);
       userDetails.addLast(goalSt);
       userDetails.addLast(gameSt);
       return userDetails;
    }
    @Override
    protected void update() throws SQLException {
        String [] params= new String[10];
        params[0]=account.getUserName();
        params[1]="";
        if(team!=null)
        {
            params[1]=team.getName();
        }
        params[2]=name;
        params[3]=String.valueOf(this.page.getPageID());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    public void setBirthDay(Date birthDay) throws SQLException {
        this.birthDay = birthDay;
        update();
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) throws SQLException {
        this.goals += goals;
    }


    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) throws SQLException {
        this.yellowCards += yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) throws SQLException {
        this.redCards += redCards;
    }

    public List<PlayerPosition> getPositions() {
        return positions;
    }

    private void setPositions(List<String> positionsSt) throws SQLException {
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

    public void updateByRefree() throws SQLException {
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
