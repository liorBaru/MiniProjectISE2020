package service;

import domain.*;

public class BoardManagerController {

     public BoardManagerController()
        {
        }
        public void addAssets(Asset asset, Owner owner) {
         if(owner==null || asset==null)
             throw new ArithmeticException("arguments are not valid");
             owner.addAsset(asset);
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
