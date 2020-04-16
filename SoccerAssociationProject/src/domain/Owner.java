package domain;

import java.util.ArrayList;
import java.util.List;

public class Owner extends BoardMember
{
        private ArrayList<StaffMember> anotherJob;


        public Owner(String userName, String password, String name, String job, Team team, BoardMember boss, ArrayList<StaffMember> anotherJob) {
                super( new Account(userName,password),name,team,boss);
                this.anotherJob = new ArrayList<>();
        }

        public void addAnotherJob(StaffMember newRole)
        {
                anotherJob.add(newRole);
        }

        public String getType(){
                return "Owner";
        }


        public void addAsset(Asset asset){
                this.getTeam().addAsset(asset);
                asset.setTeam(this.getTeam());
        }

        public void appointmentNewOwner(Owner newOwner){
                if(newOwner.team!=null){
                        throw new ArithmeticException("The owner already has a team");
                }
                team.getOwners().add(newOwner);
                this.appointments.add(newOwner);
        }
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
        public void removeTeamManger(TeamManager teamManager){
                if(this.appointments.contains(teamManager)) {
                        team.removeTeamManger(teamManager);
                }
                else {
                        throw new ArithmeticException("This is not your appointment");
                }

        }


        public void closeTeam() {
                if(team==null)
                        throw new ArithmeticException("arguments are not valid");
                this.team.setStatus(false);
                ///TODO
        }

        public void appointTeamManger(User user,List<String> permissionList,double salary) {
                if(user instanceof Owner || user instanceof TeamManager){
                        throw new ArithmeticException("already Team manger or team owner");
                }
                TeamManager teamManager=new TeamManager(user,this.team,permissionList,salary);
        }
        public void reportIncomeOrOutcome(FinancialAction financialAction){
                team.addFinancialAction(financialAction);
        }
}
