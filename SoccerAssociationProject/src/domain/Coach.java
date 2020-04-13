package domain;

public class Coach extends TeamMember implements pageable
{
    private Page page;
    private String training;


    public void uploadDataToPage(String data){}

    public Coach(String userName, String password, String name, String job, Team team, int contract, double salary, Page page, String training) {
        super(userName, password, name, job, team, contract, salary);
        this.page = page;
        this.training = training;
    }


    @Override
    public String getType() {
        return "Coach: "+this.name;
    }
}

