package domain;

public interface Asset {
    public String getType();
    public void setTeam(Team team);
    public void removeTeam();
}
