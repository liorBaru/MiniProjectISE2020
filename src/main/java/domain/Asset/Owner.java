package domain.Asset;

import domain.manageUsers.Account;
import domain.manageTeams.FinancialAction;
import domain.manageEvents.Notification;
import domain.manageTeams.Team;

import java.util.Date;
import java.util.List;

public class Owner extends BoardMember
{
        private StaffMember anotherJob;

        public Owner(Account account, String name, Team team, BoardMember boss, StaffMember anotherJob)
        {
                super(account,name,team,boss);
                this.anotherJob = anotherJob;
                setPermissions();
        }
        public Owner(Account account,String name){
                super(account,name,null);
        }

        /**
         * @author matan
         * @param asset
         * Add asset to the owner's team
         */
        public void addAsset(Asset asset)
        {
                this.getTeam().addAsset(asset);
                asset.setTeam(this.getTeam());
        }

        /**
         * @author matan
         * @param newOwner
         * Add a new owner to the owner's(call tha action) team
         */
        public void appointmentNewOwner(Owner newOwner)
        {
                if(newOwner.team!=null){
                        throw new ArithmeticException("The owner already has a team");
                }
                team.getOwners().add(newOwner);
                this.appointments.add(newOwner);
                newOwner.team=this.team;
        }

        /**
         * @author matan
         * @param removeOwner
         * remove owner that the ask for the action appoint
         * add remove all the owner appoints
         */
        public void removeOwner(Owner removeOwner)
        {
                if(this.appointments.contains(removeOwner)){
                        this.appointments.remove(removeOwner);
                        team.getOwners().removeAll(removeOwner.appointments);
                        team.getOwners().remove(removeOwner);
                        team.getStaffMembers().removeAll(appointments);
                }
                else {
                        throw new ArithmeticException("This is not your appointment");
                }
        }
        /**
         * @author matan
         * @param teamManager
         * remove  team manger from the owner's team.
         */
        public void removeTeamManger(TeamManager teamManager)
        {
                if(this.appointments.contains(teamManager)) {
                        team.removeTeamManger(teamManager);
                        teamManager.setTeam(null);
                        teamManager.cleanPermission();
                }
                else {
                        throw new ArithmeticException("This is not your appointment");
                }

        }

        /**
         * @author matan
         * set the team status to inActive
         */
        public void closeTeam() throws Exception {

             if(team!=null)
             {
                     this.team.setStatus(false);
                     String details =team.getName()+" has been closed by the owner "+this.name;
                     Date date= new Date();
                     Notification notification= new Notification(details);
                     for (StaffMember member:team.getStaffMembers())
                     {
                             if(member instanceof BoardMember)
                             {
                                     member.addNotification(notification);
                             }
                     }
                     for (SystemManager sm:system.getSystemManagers())
                     {
                             sm.addNotification(notification);
                     }
                     return;
             }
             throw new Exception("team is not connected any more");
        }
        /**
         * @author matan
         * @param teamManager
         * @param salary
         * @param permissionList
         * appoint new team manger to the owner's team with permission and salary as we given
         */
        public void appointTeamManger(TeamManager teamManager,List<String> permissionList,double salary) {
                appointments.add(teamManager);
                teamManager.setPermissions(permissionList);
                teamManager.setSalary(salary);
                this.team.addStaffMember(teamManager);

        }
        /**
         * @author matan
         * @param financialAction
         * add financial Action to owner's team
         */
        public void reportIncomeOrOutcome(FinancialAction financialAction)
        {
                team.addFinancialAction(financialAction);
        }

        @Override
        public String getType()
        {
                return "Owner:"+this.name;
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
                        this.permissions.put(possibleValues[i],true);
                }
        }

        @Override
        public void removeTeam(Team team) throws Exception
        {
                //ToDo remove owner
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
}
