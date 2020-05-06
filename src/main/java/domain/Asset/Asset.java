package domain.Asset;

import domain.manageTeams.Team;

public interface Asset {
     String getType();
     void setTeam(Team team);
     void removeTeam(Team team) throws Exception;
     String getName();


}
