package domain;

import java.util.ArrayList;
import java.util.List;

public class Owner extends BoardMember implements Asset
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


}
