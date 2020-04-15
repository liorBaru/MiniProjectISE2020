package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class Team implements pageable
{
    private String name;
    private Page page;

    public void setStatus(boolean status) {
        this.status = status;
        if(this.status!=status){
            String statusString=status==false ? " is inactive" :" is active";
            Date date = new Date();
            Notification notification=new Notification("The Team: "+name+ statusString, date);
            for (StaffMember staffMember:staffMembers) {
                staffMember.account.getUser().notifications.add(notification);
            }
            for (Owner owner:owners){
                owner.account.getUser().notifications.add(notification);
        }
            //todo acknowledge the system and archive the history of the team
        }
    }

    private boolean status;
    private List<Owner> owners;
    private List<StaffMember> staffMembers;
    private TreeMap<Integer, Game> games;
    private TreeMap<Season, TeamInfo> seasons;
    private ArrayList<Asset> assetsOfTeam;

    public Team (List<Owner> owners,String name){
        owners=new ArrayList<>();
        this.name=name;
        this.assetsOfTeam =new ArrayList<>();
    }

    public void addAsset(Asset asset)
    {
        if(asset==null || status==false)
            throw new ArithmeticException("missed asset");
        this.assetsOfTeam.add(asset);
        //TODO: write to logger
    }

    public void removeAsset(Asset asset)
    {
        if(asset==null|| status==false)
            throw new ArithmeticException("missed asset");
        if(!assetsOfTeam.contains(asset))
            throw new ArithmeticException("the asset doesnt exists");
        this.assetsOfTeam.remove(asset);
        //TODO: write to logger
    }

    public ArrayList<Asset> getAssetsOfTeam() {
        return assetsOfTeam;
    }

    public void uploadDataToPage(String data){}

    public List<Owner> getOwners() {
        return owners;
    }
}
