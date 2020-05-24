package domain.Asset;

import domain.manageTeams.Team;

import java.sql.SQLException;

public interface Asset {
     String getType();
     void setTeam(Team team) throws SQLException;
     void removeTeam(Team team) throws Exception;
     String getName();


}
