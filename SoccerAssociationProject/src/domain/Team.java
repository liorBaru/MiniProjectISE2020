package domain;

import java.util.ArrayList;
import java.util.List;
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

    public Team (List<Owner> owners,String name){
        owners=new ArrayList<>();
        this.name=name;
        assetsOfTeam =new ArrayList<>();
    }

    public void addAsset(Asset asset)
    {
        if(asset==null)
            throw new ArithmeticException("missed asset");
        this.assetsOfTeam.add(asset);
    }


    public void uploadDataToPage(String data){}




}
