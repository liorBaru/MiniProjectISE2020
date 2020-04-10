package domain;

public class StaffMember extends User
{

    private String job;
    private Team team;

    public StaffMember(String userName, String password, String name, String job, Team team) {
        super(userName, password, name);
        this.job = job;
        this.team = team;
    }

    @Override
    public String showPersonalDetails() {
        return null;
    }

    @Override
    public void updateDetailes() {

    }
}
