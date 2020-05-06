package main.domain.Asset;

import main.domain.manageTeams.Team;

public interface Asset {
     String getType();
     void setTeam(Team team);
     void removeTeam(Team team) throws Exception;
     String getName();


}
