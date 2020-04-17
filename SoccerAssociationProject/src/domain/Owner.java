package domain;

import java.util.ArrayList;
import java.util.List;

public class Owner extends BoardMember
{
        private ArrayList<StaffMember> anotherJob;

        public Owner(String userName, String password, String name, String job, Team team, BoardMember boss, ArrayList<StaffMember> anotherJob) {
                super(userName,password,name,job,team,boss);
                this.anotherJob = new ArrayList<>();
        }

        public void addAnotherJob(StaffMember newRole)
        {
                anotherJob.add(newRole);
        }

        public String getType(){
                return "Owner";
        }

        /**
         * @author matan
         * @param asset
         * Add asset to the owner's team
         */
        public void addAsset(Asset asset){
                this.getTeam().addAsset(asset);
                asset.setTeam(this.getTeam());
        }

        /**
         * @author matan
         * @param newOwner
         * Add a new owner to the owner's(call tha action) team
         */
        public void appointmentNewOwner(Owner newOwner){
                if(newOwner.team!=null){
                        throw new ArithmeticException("The owner already has a team");
                }
                team.getOwners().add(newOwner);
                this.appointments.add(newOwner);
        }

        /**
         * @author matan
         * @param removeOwner
         * remove owner that the ask for the action appoint
         * add remove all the owner appoints
         */
        public void removeOwner(Owner removeOwner){
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
        public void removeTeamManger(TeamManager teamManager){
                if(this.appointments.contains(teamManager)) {
                        team.removeTeamManger(teamManager);
                }
                else {
                        throw new ArithmeticException("This is not your appointment");
                }

        }

        /**
         * @author matan
         * set the team status to inActive
         */
        public void closeTeam() {
                if(team==null)
                        throw new ArithmeticException("arguments are not valid");
                this.team.setStatus(false);
                ///TODO
        }
        /**
         * @author matan
         * @param user
         * @param salary
         * @param permissionList
         * appoint new team manger to the owner's team with permission and salary as we given
         */
        public void appointTeamManger(User user,List<String> permissionList,double salary) {
                if(user instanceof Owner || user instanceof TeamManager){
                        throw new ArithmeticException("already Team manger or team owner");
                }
                TeamManager teamManager=new TeamManager(user,this.team,permissionList,salary);
        }
        /**
         * @author matan
         * @param financialAction
         * add financial Action to owner's team
         */
        public void reportIncomeOrOutcome(FinancialAction financialAction){
                team.addFinancialAction(financialAction);
        }
}
