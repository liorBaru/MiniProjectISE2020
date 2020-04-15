package domain;

public abstract class StaffMember extends User implements Asset
{
    protected Team team;
    protected BoardMember boss;

    public StaffMember(Account account, String name,Team team, BoardMember boardMember)
    {
        super(name,account);
        this.team=team;
        this.boss=boardMember;
    }

    public StaffMember (Account account, String name, Team team)
    {
        super(name,account);
        this.team=team;
    }


    public void setTeam(Team team)
    {
        this.team = team;
    }

    @Override
    public abstract String getType();

    @Override
    public void removeUser()
    {
        removeTeam(team);
    }

    public Team getTeam() {
        return team;
    }
        @Override
    public String showPersonalDetails() {
        return null;
    }

    @Override
    public void updateDetailes() {

    }
}
