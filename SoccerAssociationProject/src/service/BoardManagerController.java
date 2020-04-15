package service;

import domain.*;

public class BoardManagerController {

     public BoardManagerController()
        {
        }

    /**
     * @author matan
     * @param asset
     * @param owner
     * add assets to the owner's team
     * UC 6.1
     */
        public void addAssets(Asset asset, Owner owner) {
         if(owner==null || asset==null)
                throw new ArithmeticException("arguments are not valid");
            owner.addAsset(asset);
        }

    /**
     * @author matan
     * @param asset
     * @param owner
     * remove assets form the owner's team
     * UC 6.1
     */
        public void removeAssets(Asset asset, Owner owner)
        {
            if(owner==null || asset==null)
                throw new ArithmeticException("arguments are not valid");
           owner.getTeam().removeAsset(asset);

        }
        public void appointment(Owner owner,User newAppointment){
            if(newAppointment==null ||owner==null)
                throw new ArithmeticException("arguments are not valid");
            ///TODO

        }
        public void closeTime(Owner owner){
         owner.closeTeam();

        }



}
