package domain.Asset;

import DataAccess.System;
import domain.manageTeams.Team;
import domain.manageUsers.Account;
import domain.manageEvents.Notification;
import DataAccess.OwnersDaoSql;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Owner extends BoardMember
{
        private static OwnersDaoSql ownersDauSql =OwnersDaoSql.getInstance();
        private StaffMember anotherJob;

        public Owner(Account account, String name, Team team, BoardMember boss, StaffMember anotherJob) throws SQLException {
                super(account,name,team,boss,"Owner");
                String anotherJobS="";
                if(anotherJob!=null)
                {
                    anotherJobS=anotherJob.getType();
                }
                String []params={account.getUserName(),name,team.getName(),team.getName(),anotherJobS};
                ownersDauSql.save(params);
                this.anotherJob = anotherJob;
                setPermissions();
        }


        public Owner(Account account,String name) throws SQLException {
                super(account,name,null);
                String[] params={account.getUserName(),name,"",""};
                String[] key={account.getUserName(),account.getUserName()};
                apointmentsDaoSql.save(key);
                ownersDauSql.save(params);
        }

        public Owner(Account account,String name, Team team, StaffMember anotherJob)
        {
                super(account,name,team);
                this.anotherJob=anotherJob;
        }

        public static Owner getOwnerFromDB(String[] ownerName) throws Exception
        {
                List<String []> owners= ownersDauSql.get(ownerName);
                if(owners.isEmpty())
                {
                        throw new Exception("username not found in system");
                }
                String [] ownerDetails = owners.get(0);
                return createOwnerFromDB(ownerDetails);
        }

        public static Owner createOwnerFromDB(String[] ownerDetails) throws Exception {
                System system=System.getInstance();
                Account account= system.getAccountManager().getAccount(ownerDetails[0]);
                String name="";
                Team team=null;
                StaffMember anotherJob=null;
                if(ownerDetails[1]!=null)
                {
                        name=ownerDetails[1];
                }
                if(ownerDetails[2]!=null && ownerDetails[2].isEmpty()==false)
                {
                        team = Team.createTeamFromDB(ownerDetails[2]);
                }
                if(ownerDetails[3]!=null && ownerDetails[3].isEmpty()==false)
                {
                        if(ownerDetails[3].equals("Coach"))
                        {
                                anotherJob = Coach.getCoachFromDB(ownerDetails);
                        }
                        else if(ownerDetails[3].equals("Player"))
                        {
                                anotherJob=Player.getPlayerFromDB(ownerDetails);
                        }
                }
                Owner owner = new Owner(account,name,team,anotherJob);
                owner.setPermissions();
                return owner;
        }


        /**
         * @author matan
         * @param newOwner
         * Add a new owner to the owner's(call tha action) team
         */
        public void appointmentNewOwner(Owner newOwner) throws Exception {
                if(newOwner.team!=null)
                {
                        throw new ArithmeticException("The owner already has a team");
                }
                team.addStaffMember(newOwner);
                String[] key={this.account.getUserName(),newOwner.getAccount().getUserName()};
                apointmentsDaoSql.save(key);
                newOwner.setTeam(this.team);
        }

        /**
         * @author matan
         * @param removeOwner
         * remove owner that the ask for the action appoint
         * add remove all the owner appoints
         */
        public void removeOwner(Owner removeOwner) throws Exception
        {
                if(this.team!=null && removeOwner.team!=null)
                {
                        String[] key={"Key",this.account.getUserName(),removeOwner.account.getUserName()};
                        List<String[]> appointments=apointmentsDaoSql.get(key);
                        if(appointments!=null && appointments.isEmpty()==false)
                        {
                                String[] params= {this.account.getUserName(),removeOwner.account.getUserName()};
                                apointmentsDaoSql.delete(params);
                                removeOwner.removeTeam(removeOwner.team);
                        }
                        else
                        {
                                throw new Exception("invalid operation");
                        }
                }
        }
        /**
         * @author matan
         * @param teamManager
         * remove  team manger from the owner's team.
         */
        public void removeTeamManger(TeamManager teamManager) throws Exception
        {
                if(this.team!=null && teamManager.team!=null)
                {
                        String[] key={"Key",this.account.getUserName(),teamManager.getAccount().getUserName()};
                        List<String[]> appointments=apointmentsDaoSql.get(key);
                        if(appointments!=null && appointments.isEmpty()==false)
                        {
                                String[] params= {this.account.getUserName(),teamManager.getAccount().getUserName()};
                                apointmentsDaoSql.delete(params);
                                teamManager.removeTeam(teamManager.team);
                        }
                        else
                        {
                                throw new Exception("invalid operation");
                        }
                }
        }

        /**
         * @author matan
         * set the team status to inActive
         */
        public void closeTeam() throws Exception
        {
             if(team!=null)
             {
                     this.team.setStatus(false);
                     String details =team.getName()+" has been closed by the owner "+this.name;
                     Notification notification= new Notification(details);
                     for (StaffMember member:team.getStaffMembers())
                     {
                             if(member instanceof BoardMember)
                             {
                                     member.addNotification(notification);
                             }
                     }
                     system.sendNotificationToSystemManager(notification);
                     return;
             }
             throw new Exception("team is not connected any more");
        }
        /**
         * @author matan
         * @param teamManager
         * @param permissionList
         * appoint new team manger to the owner's team with permission and salary as we given
         */
        public void appointTeamManger(TeamManager teamManager,List<String> permissionList) throws SQLException
        {
                String[] key ={this.account.getUserName(),teamManager.getAccount().getUserName()};
                apointmentsDaoSql.save(key);
                teamManager.setPermissions(permissionList);
                this.team.addStaffMember(teamManager);
        }

        @Override
        public String getType()
        {
                return "Owner";
        }
        public void openTeam() throws Exception
        {
                if(team==null)
                        throw new ArithmeticException("arguments are not valid");
                this.team.setStatus(true);
        }

        /**
         * gal
         * set owner permissions
         */
        private void setPermissions()
        {
                permission[] possibleValues = permission.values();
                for(int i=0;i<possibleValues.length;i++)
                {
                        this.permissions.add(possibleValues[i]);
                }
        }

        @Override
        public void removeTeam(Team team) throws Exception
        {
                if(this.team.isStatus() && this.boss==null)
                {

                        throw new Exception("you cant delete from the system team owner.");
                }
                else
                {
                        for (StaffMember appoint:getAppointments())
                        {
                                appoint.removeTeam(team);
                        }
                        if(this.team!=null)
                        {
                                this.team.removeStaffMember(this);
                                this.team=null;
                        }
                }
                update();
        }

        @Override
        public boolean equals(Object object)
        {
                Owner owner = (Owner)object;
                if(owner.getName().equals(this.name))
                {
                        return true;
                }
                return false;
        }

        @Override
        public LinkedList<String> showPersonalDetails()
        {
                LinkedList<String> userDetails =super.showPersonalDetails();
                userDetails.addFirst("Owner");
                String anotherjobS="";
                if(this.anotherJob!=null)
                {
                        anotherjobS=anotherJob.getType();
                }
                userDetails.addLast(anotherjobS);
                return userDetails;
        }

        @Override
        protected void update() throws SQLException {
                String [] params = new String[4];
                params[0]=this.account.getUserName();
                params[1]=this.getName();
                params[2]="";
                if(team!=null)
                {
                        params[2]=team.getName();
                }
                params[3]="";
                if(anotherJob!=null)
                {
                        params[3]=anotherJob.getType();
                }
                ownersDauSql.update(params);
        }
}
