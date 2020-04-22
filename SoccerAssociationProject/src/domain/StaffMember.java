package domain;

import java.util.List;

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
    public StaffMember(Account account, String name)
    {
        super(name,account);
    }

    public StaffMember(Account account, String name)
    {
        super(name,account);
    }


    public void setTeam(Team team)
    {
        this.team = team;
    }

    @Override
    public abstract String getType();

    @Override
    public void removeUser() throws Exception {
        this.removeTeam(team);
    }

    public Team getTeam() {
        return team;
    }

    /**
     * gal
     * show staffMember personal details
     * @return
     */

    @Override
    public List<String> showPersonalDetails()
    {
        List<String> userDetails= super.showPersonalDetails();
        String teamString = "Team: "+ team.getName();
        userDetails.add(teamString);
        return userDetails;
    }
}
