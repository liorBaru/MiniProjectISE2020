package domain.Asset;

import DataAccess.ComplaintDaoSql;
import DataAccess.SystemManagerDaoSql;
import DataAccess.TeamDaoSql;
import domain.manageEvents.Complaint;
import domain.managePages.Page;
import domain.manageTeams.Team;
import domain.manageUsers.Account;
import domain.manageUsers.User;
import domain.manageEvents.Notification;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class SystemManager extends User
{
    /**
     * gal,
     * get all new complaints from the system
     * @return
     */

    private static SystemManagerDaoSql systemManagerDaoSql;
    private static ComplaintDaoSql complaintDaoSql;
    private static TeamDaoSql teamDaoSql;

    public SystemManager (String name, Account account) throws SQLException {
        super(name,account);
        String[] params={account.getUserName(),name};
        systemManagerDaoSql.save(params);
    }

    @Override
    public LinkedList<String> showPersonalDetails()
    {
        LinkedList<String> userDetails= super.showPersonalDetails();
        userDetails.addFirst("SystemManager");
        return userDetails;
    }

    public SystemManager (String [] details)
    {
        this.account=system.getAccountManager().getAccount(details[0]);
        this.name=details[1];
    }

    public static SystemManager createSystemManagerFromDB(String[] key) throws Exception {
        List<String[]> systemManagerList=systemManagerDaoSql.get(key);
        if(systemManagerList==null || systemManagerList.isEmpty())
        {
            throw new Exception("wrong arguments");
        }
        SystemManager systemManager = new SystemManager(systemManagerList.get(0));
        return systemManager;
    }

    public TreeMap<Integer,String> watchComplaints()
    {
        TreeMap<Integer,String> complaints = new TreeMap<>();
        for (String[] complaint :complaintDaoSql.getAll())
        {
            if(Boolean.valueOf(complaint[1]))
            {
                int id=Integer.valueOf(complaint[0]);
                String details=complaint[2];
                complaints.put(id,details);
            }
        }
        return complaints;
    }

    public List<String> getTeams()
    {
        List<String> teamNames= new LinkedList<>();
        for (String[] team :teamDaoSql.getAll())
        {
            teamNames.add(team[0]);
        }
        return teamNames;
    }


    /**
     * remove user from the system
     * @param userName
     * @return
     * @throws Exception
     */
    public boolean removeUserFromSystem (String userName) throws Exception
    {
        Account account = system.getAccountManager().getAccount(userName);
        User user = account.getUser();
        if(user!=null)
        {
            user.removeUser();
            return system.getAccountManager().removeAccount(account);
        }
        return false;
    }

    @Override
    public boolean removeUser() throws Exception {
        throw new Exception("you cant delete system manager from the system");
    }

    public void closeTeam (String teamName) throws Exception
    {
        for (String[] teamS:teamDaoSql.getAll())
        {
            if(teamS[0].equals(teamName))
            {
                String details ="Team : "+teamName+" has been closed by the systemManager";
                Notification notification = new Notification(details);
                Team team=Team.createTeamFromDB(teamName);
                team.setClose(notification);
                String [] key={teamName};
                Page page =team.getPage();
                teamDaoSql.delete(key);
                page.deletePage();
                return;
            }
        }
        throw new Exception("Team "+teamName+" is not active team in the system");
    }

    /**
     * gal
     * answer Complaint by system manager
     * @param complaintID
     * @param answer
     * @throws Exception
     */
    public void answerComplaint (int complaintID, String answer ) throws Exception
    {
        if(answer.isEmpty()==false)
        {
            String [] key={String.valueOf(complaintID),answer};
            List<String[]> complaints=complaintDaoSql.get(key);
            if(complaints==null ||complaints.isEmpty())
            {
                throw new Exception ("wrong complaint id");
            }
            Complaint complaint =Complaint.createComplaintFromSql(complaints.get(0));
            if(complaint.getStatus())
            {
                complaint.setAnswer(answer);
                return;
            }
            throw new Exception("complaint has been answer already");
        }
    }

    @Override
    protected void update() throws SQLException {
        String []key ={account.getUserName(),name};
        systemManagerDaoSql.update(key);
    }






}
