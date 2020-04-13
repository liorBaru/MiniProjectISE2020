package domain;

public abstract class StaffMember extends User implements Asset
{

    private String job;
    protected Team team;

    public StaffMember(String userName, String password, String name, String job, Team team) {
        super(userName, password, name);
        this.job = job;
        this.team = team;
    }
    public String getJob() {
        return job;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public abstract String getType();


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
