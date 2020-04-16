package domain;

import java.util.ArrayList;
import java.util.List;

public class Owner extends BoardMember
{
        private StaffMember anotherJob;

        public Owner(Account account, String name, Team team, BoardMember boss)
        {
                super(account, name,team, boss);
                setPremissions();
        }

        public Owner(Account account, String name, Team team, BoardMember boss, StaffMember user)
        {
                super(account, name,team, boss);
                this.anotherJob = user;
                setPremissions();
        }

        private void setPremissions()
        {
                for (premission premission:premission.values())
                {
                        permissions.put(premission,true);
                }
        }

        public void closeTeam()
        {
                String  details = "the team, "+name+", was closed by the owner";
                Notification closeNotification = new Notification(details);
                team.setClose(closeNotification);
                system.closeTeam(closeNotification);
        }

        public void addAnotherJob(StaffMember newRole)
        {
                if(anotherJob!=null && (anotherJob instanceof Owner)==false)
                {
                        anotherJob=newRole;
                }
        }

        public String getType(){
                return "Owner";
        }

        @Override
        public void removeTeam(Team team)
        {
                if(this.team.getName()==team.getName() && this.boss!=null)
                {
                        super.removeTeam(team);
                }
                else
                {
                        try
                        {
                                throw new Exception("The team must have Owner ");
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        public boolean addManagerToTeam(String userName, String password ,String name, double salary , List<String> premissions)
        {
                Account account = system.addBoardMember(userName,password, name);
                if(account!=null && account.getUser()==null)
                {
                        BoardMember manger = new TeamManager(account,name,team,this,salary,premissions);
                        account.setUser(manger);
                        this.appointments.add(manger);
                        team.addAsset(manger);
                        team.addStaffMember(manger);
                        return true;
                }
                return false;
        }

        public boolean addOwnerToTeam(String userName,String passowrd ,String name)
        {
                Account account = system.addBoardMember(userName , passowrd , name);
                if(account!=null)
                {
                        BoardMember owner;
                        if(account.getUser()==null)
                        {
                                owner= new Owner(account,name,team,this);
                                account.setUser(owner);
                        }
                        else if(account.getUser() instanceof Player || account.getUser() instanceof Coach || account.getUser() instanceof TeamManager)
                        {
                                owner = new Owner(account,name,team,this,(StaffMember)account.getUser());
                                account.setUser(owner);
                        }
                        else
                        {
                                return false;
                        }
                        this.appointments.add(owner);
                        team.addStaffMember(owner);
                        team.addAsset(owner);
                        return true;
                }
                return false;
        }
}
