package main.service;


import main.domain.Asset.BoardMember;
import main.domain.Asset.Owner;
import main.domain.Asset.TeamManager;
import main.domain.manageUsers.Guest;

import java.sql.SQLException;
import java.util.List;

public class BoardManagerController extends GuestController {

    private BoardMember boardMember;

    public BoardManagerController(Guest guest) {
        super(guest);
    }

    public void BoardManagerController(BoardMember boardMember)
    {
        this.boardMember=boardMember;
    }
    /**
     * @author matan
     * add assets to the owner's team
     * UC 6.1
     */
        public void addAssets(String name,String type, BoardMember boardMember)
        {
            if(boardMember==null || name.isEmpty() || type.isEmpty())
                throw new ArithmeticException("arguments are not valid");
            try
            {
             boardMember.addAssets(name,type);
            }
            catch (Exception e)
            {
             e.printStackTrace();
            }
        }

    /**
     * @author matan
     * @param asset
     * @param boardMember
     * remove assets form the owner's team
     * UC 6.1
     */
        public void removeAssets(String asset, BoardMember boardMember)
        {
            if(boardMember==null || asset==null ||asset.isEmpty())
                throw new ArithmeticException("arguments are not valid");
            try
            {
                boardMember.removeAsset(asset);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    /**
     * @author matan
     * @param owner
     * @param newOwner
     * appoint a new owner to the time's owner UC 6.2
     */
        public void appointmentNewOwner(Owner owner,Owner newOwner) throws Exception {
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
    public void removeOwnerAppointment(Owner owner,Owner removeOwner)
    {
        try
        {
            owner.removeOwner(removeOwner);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *@author matan
     * @param owner
     * @param user
     * appoint team manger to owner's team UC 6.4
     */
    public void appointTeamManger(Owner owner, TeamManager user, List<String>permissionList,double salary ) throws SQLException {
        owner.appointTeamManger(user,permissionList);

    }

    /**
     * @author matan
     * @param owner
     * @param teamManager
     * remove team manger from owner's team UC 6.5
     */
    public void removeTeamManger(Owner owner,TeamManager teamManager)
    {
        try
        {
            owner.removeTeamManger(teamManager);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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
     * @param boardMember
     * @param detailes
     * @param price
     * Report of income or outcome of the owner's team UC 6.7
     */
        public void reportIncomeOrOutcome(BoardMember boardMember,String detailes,double price)
        {
            boardMember.addFinancialAction(detailes,price);
        }



}
