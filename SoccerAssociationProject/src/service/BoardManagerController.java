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
            if(user==null || asset==null)
                throw new ArithmeticException("arguments are not valid");
            asset.removeTeam();
        }
        public void appointment(User newAppointment){

        }


}
