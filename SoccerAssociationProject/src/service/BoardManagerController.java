package service;

import domain.*;

public class BoardManagerController {

     public BoardManagerController()
     {
     }

        public void addAssets(Asset asset, User user)
        {
            Team ownerTeam=((Owner)user).getTeam();
            ownerTeam.addAsset(asset);
            if(asset instanceof TeamManager || asset instanceof TeamMember )
                ((TeamMember)asset).setTeam(ownerTeam);
        }

        public void removeAssets(Asset asset, User user)
        {
            Team ownerTeam=((Owner)user).getTeam();
            ownerTeam.removeAsset(asset);
            if(asset instanceof TeamManager || asset instanceof TeamMember )
                ((StaffMember)asset).setTeam(null);
        }
        public void appointment(User newAppointment){

        }


}
