package domain;

import java.util.List;
import java.util.TreeMap;

public class Team
{
    private String name;
    private Page page;
    private boolean status;
    private List<Owner> owners;
    private List<StaffMember> staffMembers;
    private TreeMap<Integer, Game> games;
    private TreeMap<Season, TeamInfo> seasons;


    public Team (List<Owner> owners,String name){}




    public void uploadDataToPage(String data){}




}
