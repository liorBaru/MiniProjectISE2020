package main.service;

import main.domain.Asset.SystemManager;
import main.domain.Cotrollers.*;
import main.domain.manageUsers.Guest;
import main.domain.manageUsers.User;

import java.util.Observable;
import java.util.Observer;

public class GuestApplication extends Observable implements Observer
{
    Guest guest;
    User currentUser;
    BoardManagerController boardManagerController;
    FanController fanController;
    IFAController ifaController;
    LeagueController leagueController;
    RefreeController refreeController;
    SystemManagerController systemManagerController;
    TeamMemberController teamMemberController;

    public GuestApplication(Guest guest) {
        this.guest=guest;
        createControllers();
    }

    public boolean register(String name, String userName, String password) throws Exception {
        if(name!=null && userName!=null && password!=null)
        {
            if(userName.length()>6 && password.length()>8)
            {
                if(guest.register(name,userName,password)!=null)
                    return true;
            }
            return false;
        }
        return false;
    }


    public void createControllers(){
        this.boardManagerController= new BoardManagerController();
        this.fanController= new FanController();
        this.ifaController= new IFAController();
        this.leagueController= new LeagueController();
        this.refreeController=new RefreeController();
        this.systemManagerController=new SystemManagerController();
        this.teamMemberController= new TeamMemberController();
    }

    public String login( String userName, String password) throws Exception {
        if( userName!=null && password!=null)
        {
            User user= guest.login(userName,password);
            if(user!=null){
                currentUser=user;
                return user.getKind();
            }
        }
        return "invalid";
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    /**
     *
     * @param team
     * @param owner
     * @return message:
     * "no owner" / "success"
     * @throws Exception
     */
    public String openTeam(String team, String owner) throws Exception {
        systemManagerController.setSystemManager((SystemManager)currentUser);
         return systemManagerController.openTeam(team,owner );
    }
}
