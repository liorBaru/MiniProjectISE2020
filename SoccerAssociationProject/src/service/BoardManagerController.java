package service;

import domain.*;

import java.util.List;

public class BoardManagerController
{
     private BoardMember boardMember;

     public BoardManagerController(BoardMember boardMember)
     {
         this.boardMember=boardMember;
     }

     public void addAssets(String name)
     {
         boardMember.addAssets(name);
     }

     public boolean addManager(String userName,String name, double salary, List<String> premissions)
     {
         if(boardMember instanceof  Owner)
         {
             return ((Owner) boardMember).addManagerToTeam(userName,name,salary,premissions);

         }
         return false;
     }

     public boolean addOwner(String userName, String name)
     {
         if(boardMember instanceof Owner)
         {
             return ((Owner) boardMember).addOwnerToTeam(userName,name);
         }
         return false;
     }

     public boolean closeTeam()
     {
         if(boardMember instanceof Owner)
         {
             ((Owner)boardMember).closeTeam();
              return true;
         }
         return false;
     }

     public void uploadDateToPage(String message)
     {
         if(!message.isEmpty())
         {
             boardMember.updateTeamPage(message);
         }
     }

     public void addPlayer(String playerName)
     {
         boardMember.addPlayer(playerName);
     }

     public void removeAssets(Asset asset, User user)
     {
         Team ownerTeam=((Owner)user).getTeam();
         ownerTeam.removeAsset(asset);
         if(asset instanceof TeamManager || asset instanceof TeamMember )
             ((StaffMember)asset).setTeam(null);
     }

}
