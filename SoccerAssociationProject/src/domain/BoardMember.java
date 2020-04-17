package domain;

import java.util.List;

public abstract class  BoardMember extends StaffMember
{
    private BoardMember boss;
    protected List<StaffMember> appointments;


    public BoardMember(String userName, String password, String name, String job, Team team, BoardMember boss) {
        super(new Account(userName,password),name,team);
        this.boss = boss;
    }
    public BoardMember(Account account,String name,String job,Team team,BoardMember boss){
        super(account,name,team,boss);
    }

    @Override
    public void removeTeam(Team team) {
        team.removeAsset(this);
    }

}
