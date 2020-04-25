package domain.manageTeams;

import domain.Asset.Asset;
import domain.Asset.Owner;
import domain.Asset.StaffMember;
import domain.Asset.TeamManager;
import domain.manageEvents.Notification;
import domain.manageLeagues.Game;
import domain.manageLeagues.Season;
import domain.managePages.Page;
import domain.managePages.pageable;

import java.util.*;

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


    public Team(List<Owner> owners, String name)
    {
        this.owners = owners;
        this.name = name;
        page = new Page(this);
        status=true;
        staffMembers=new LinkedList<>();
        games= new TreeMap<>();
        seasons=new TreeMap<>();
        this.assetsOfTeam = new ArrayList<>();
        this.financialActions=new HashSet<>();

    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
//    public Team (Owner owners, String name){
//        this.owners = new ArrayList<>();
//        String userName = owners.getAccount().getUserName();
//        String pass = owners.getAccount().getPassword();
//        owners.addOwnerToTeam(userName, pass, name);
//        this.name=name;
//        this.assetsOfTeam =new ArrayList<>();
//    }


    public List<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    public void setStatus(boolean status) throws Exception {
        if (this.status != status) {
            this.status = status;
            String statusString = status == false ? " is inactive" : " is active";
//            if(status==false){
//                statusString=" is inactive";
//            }
//            else {
//                statusString=" is active";
//            }
            Date date = new Date();
            Notification notification = new Notification("The Team: " + name + statusString, date);
            for (StaffMember staffMember : staffMembers) {
                staffMember.getAccount().getUser().addNotification(notification);
            }
            for (Owner owner : owners) {
                owner.getAccount().getUser().addNotification(notification);
            }
            //todo acknowledge the system and archive the history of the team
        }
        else if(status==false && this.status==false){
            throw new Exception("The team is not active already ");
        }
        else {
            throw new Exception("The team is active already ");
        }
    }

    public boolean isStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public boolean addFinancialAction(FinancialAction financialAction)
    {
        if (financialAction != null) {
            if (financialActions.contains(financialAction) == false) {
                financialActions.add(financialAction);
                return true;
            }
        }
        return false;
    }

    public Set<FinancialAction> getFinancialActions()
    {
        return financialActions;
    }


    public void addAsset(Asset asset) {
        if (asset == null || status == false)
            throw new ArithmeticException("missed asset");
        this.assetsOfTeam.add(asset);
        //TODO: write to logger
    }

    public void addStaffMember(StaffMember member) {
        if (member != null) {
            staffMembers.add(member);
            member.setTeam(this);
        }
    }

    public void setClose(Notification notification)
    {
        this.status = false;
        for (StaffMember member : staffMembers) {
            member.addNotification(notification);
        }
    }

    public Page getPage() {
        return page;
    }

    public void removeStaffMember(StaffMember member) {
        if (member != null && staffMembers.contains(member)) {
            staffMembers.remove(member);
        }
    }

    public void removeAsset(Asset asset) {
        if (asset == null || status == false)
            throw new ArithmeticException("missed asset");
        if (!assetsOfTeam.contains(asset))
            throw new ArithmeticException("the asset doesnt exists");
        this.assetsOfTeam.remove(asset);
        //TODO: write to logger
    }

    public ArrayList<Asset> getAssetsOfTeam() {
        return assetsOfTeam;
    }

    public void uploadDataToPage(String data) {
        if (data.isEmpty() == false) {
            page.addDataToPage(data);
        }
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void removeTeamManger(TeamManager teamManager) {
        StaffMember tempTM = null;
        for (StaffMember staff : staffMembers) {
            if (staff instanceof TeamManager && teamManager == staff) {
                tempTM = staff;
                break;
            }
        }
        if (tempTM != null) {
            staffMembers.remove(tempTM);
        } else {
            throw new IllegalArgumentException("The TeamManger is not in this team");
        }
    }
}
