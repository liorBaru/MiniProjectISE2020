package domain;

import java.util.ArrayList;
import java.util.List;

public class Owner extends BoardMember
{
        private ArrayList<StaffMember> anotherJob;


        public Owner(String userName, String password, String name, String job, Team team, BoardMember boss, ArrayList<StaffMember> anotherJob) {
                super(userName, password, name, job, team, boss);
                this.anotherJob = new ArrayList<>();
        }

        public void addAnotherJob(StaffMember newRole)
        {
                anotherJob.add(newRole);
        }

        public String getType(){
                return "Owner";
        }

        @Override
        public void removeTeam() {
                if(this.getTeam().getOwners().size()==1)
                        try {
                                throw new Exception("You are the only owner");
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                super.removeTeam();
        }

        public void addAsset(Asset asset){
                this.getTeam().addAsset(asset);
                asset.setTeam(this.getTeam());
        }


        public void closeTeam() {
                if(team==null)
                        throw new ArithmeticException("arguments are not valid");
                this.team.setStatus(false);
                ///TODO
        }
}
