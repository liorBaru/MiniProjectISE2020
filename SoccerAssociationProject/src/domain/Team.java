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

    public String getName() {
        return name;
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

    public Team (List<Owner> owners, String name) throws Exception {
        this.owners=new ArrayList<>();
        if(owners.isEmpty())
            throw new Exception("Team must have owner");
        this.name=name;
        this.assetsOfTeam =new ArrayList<>();
    }

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

    public void setClose(Notification notification)
    {
        this.status=false;

        for (StaffMember member:staffMembers)
        {
            member.addNotification(notification);
        }
    }

    public Page getPage()
    {
        return page;
    }

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

    public ArrayList<Asset> getAssetsOfTeam()
    {
        return assetsOfTeam;
    }

    public void uploadDataToPage(String data)
    {
        if(data.isEmpty()==false)
        {
            page.addDataToPage(data);
        }
    }

    public List<Owner> getOwners() {
        return owners;
    }
}
