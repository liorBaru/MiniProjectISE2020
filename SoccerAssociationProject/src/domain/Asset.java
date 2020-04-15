package domain;

public interface Asset {
     String getType();
     void setTeam(Team team);
     void removeTeam(Team team);
     String getName();


}
