package main.service;

import main.domain.Asset.Asset;
import main.domain.Asset.Owner;
import main.domain.Asset.TeamManager;
import main.domain.manageTeams.FinancialAction;

import java.util.List;

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
     * @param removeOwner
     * remove appoint of a owner to the time's owner UC 6.3
     */
    public void removeOwnerAppointment(Owner owner,Owner removeOwner){
        owner.removeOwner(removeOwner);
    }

    /**
     *@author matan
     * @param owner
     * @param user
     * appoint team manger to owner's team UC 6.4
     */
    public void appointTeamManger(Owner owner, TeamManager user, List<String>permissionList,double salary ){
        owner.appointTeamManger(user,permissionList,salary);

    }

    /**
     * @author matan
     * @param owner
     * @param teamManager
     * remove team manger from owner's team UC 6.5
     */
    public void removeTeamManger(Owner owner,TeamManager teamManager){
        owner.removeTeamManger(teamManager);
    }

    /**
     * @author matan
     * @param owner
     * Close team's owner UC 6.6
     */
        public void closeTeam(Owner owner) throws Exception {
         owner.closeTeam();
        }
    /**
     * @author matan
     * @param owner
     * Open team's owner UC 6.6
     */
    public void openTeam(Owner owner) throws Exception {
        owner.openTeam();
    }

    /**
     * @author matan
     * @param owner
     * @param financialAction
     * Report of income or outcome of the owner's team UC 6.7
     */
        public void reportIncomeOrOutcome(Owner owner, FinancialAction financialAction){
            owner.reportIncomeOrOutcome(financialAction);
        }



}
