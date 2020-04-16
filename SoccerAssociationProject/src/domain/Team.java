package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Team implements pageable
{
    private String name;
    private Page page;
    private boolean status;
    private List<Owner> owners;
    private List<StaffMember> staffMembers;
    private TreeMap<Integer, Game> games;
    private TreeMap<Season, TeamInfo> seasons;
    private ArrayList<Asset> assetsOfTeam;
    private Set<FinancialAction> financialActions;

    public Team (List<Owner> owners, String name){
        owners=new ArrayList<>();
        this.name=name;
        this.assetsOfTeam =new ArrayList<>();
    }

    //--------------------------------------------------------------------------------geeters

    public String getName() {
        return name;
    }
    public Page getPage()
    {
        return page;
    }

    public List<Owner> getOwners() {
        return owners;
    }
    public ArrayList<Asset> getAssetsOfTeam()
    {
        return assetsOfTeam;
    }
    //--------------------------------------------------------------------------------setters
    public void setClose(Notification notification)
    {
        this.status=false;

        for (StaffMember member:staffMembers)
        {
            member.addNotification(notification);
        }
    }

    //-----------------------------------------------------------------------------------add
    public void addAsset(Asset asset)
    {
        if(asset==null)
            throw new ArithmeticException("missed asset");
        this.assetsOfTeam.add(asset);
        //TODO: write to logger
    }

    public void addStaffMember(StaffMember member)
    {
        if(member!=null)
        {
            staffMembers.add(member);
        }
    }
    public boolean addFinancialAction(FinancialAction financialAction)
    {
        if(financialAction!=null)
        {
            if(financialActions.contains(financialAction)==false)
            {
                financialActions.add(financialAction);
                return true;
            }
        }
        return false;
    }

    //-----------------------------------------------------------------------------------------remove
    public void removeStaffMember(StaffMember member)
    {
        if(member!=null && staffMembers.contains(member))
        {
            staffMembers.remove(member);
        }
    }

    public void removeAsset(Asset asset)
    {
        if(asset==null)
            throw new ArithmeticException("missed asset");
        if(!assetsOfTeam.contains(asset))
            throw new ArithmeticException("the asset doesnt exists");
        this.assetsOfTeam.remove(asset);
        //TODO: write to logger
    }

    //---------------------------------------------------------------------------------------------update
    public void uploadDataToPage(String data)
    {
        if(data.isEmpty()==false)
        {
            page.addDataToPage(data);
        }
    }

}//class
