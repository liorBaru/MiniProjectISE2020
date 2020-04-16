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

    /**
     * @author matan
     * @param owner
     * @param newOwner
     * appoint a new owner to the time's owner UC 6.2
     */
        public void appointmentNewOwner(Owner owner,Owner newOwner){
            if(newOwner==null ||owner==null)
                throw new ArithmeticException("arguments are not valid");
            owner.appointmentNewOwner(newOwner);
        }
    /**
     * @author matan
     * @param owner
     * Close team's owner UC 6.6
     */
        public void closeTime(Owner owner){
         owner.closeTeam();

        }



}
