package gui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;


@Route(value = "IFA", layout = MainLayout.class)
public class IFAView extends VerticalLayout
{
    HorizontalLayout mainIFA, createTeam, createPolicy;
    VerticalLayout createTeamAddOwner, createTeamExistUser;
    VerticalLayout calculatePointsAndPlace, calculateInlayPolicy;

    public IFAView(@Autowired GuestApplication service){
        Button btn_CreateTeam=new Button("Create Team");
        Button btn_Policy=new Button("Setting Policy");
        mainIFA =new HorizontalLayout(btn_CreateTeam,btn_Policy);
        mainIFA.addClassName("ifa-div");

        //create team layer
        Button btn_existsOwner=new Button("existsOwner");
        Button btn_newOwner=new Button("New Owner");

        createTeam =new HorizontalLayout(btn_existsOwner,btn_newOwner);
        createTeam.addClassName("ifa-div");
        createTeamExistUser= createTeamExistUserLayout(service);
        createTeamAddOwner= createTeamAddOwnerLayout(service);
        createTeamExistUser.setAlignItems(Alignment.CENTER);
        createTeamAddOwner.setAlignItems(Alignment.CENTER);
        Button btn_calculatePointsAndPlace=new Button("Calculate Points And Place");
        Button btn_calculateInlayPolicy =new Button("Calculate Inlay Policy");
        createPolicy =new HorizontalLayout(btn_calculatePointsAndPlace,btn_calculateInlayPolicy);
        calculatePointsAndPlace= calculatePointsAndPlaceLayout(service);
        calculateInlayPolicy= calculateInlayPolicyLayout(service);
        calculatePointsAndPlace.setHorizontalComponentAlignment(Alignment.CENTER);
        calculateInlayPolicy.setHorizontalComponentAlignment(Alignment.CENTER);

        add(mainIFA,calculateInlayPolicy,calculatePointsAndPlace,createTeam,createPolicy,createTeamAddOwner,createTeamExistUser);

        //hide

        calculateInlayPolicy.setVisible(false);
        calculatePointsAndPlace.setVisible(false);
        createTeamAddOwner.setVisible(false);
        createTeam.setVisible(false);
        createTeamExistUser.setVisible(false);
        createPolicy.setVisible(false);


        btn_CreateTeam.addClickListener(e ->{
           this.getChildren().forEach(x-> x.setVisible(false));
           createTeam.setVisible(true);
        });

        btn_Policy.addClickListener(e ->{
            this.getChildren().forEach(x-> x.setVisible(false));
            createPolicy.setVisible(true);
        });

        btn_newOwner.addClickListener(e ->{
            this.getChildren().forEach(x-> x.setVisible(false));
            createTeamAddOwner.setVisible(true);
            createTeamAddOwner.setAlignItems(Alignment.CENTER);
        });

        btn_existsOwner.addClickListener(e ->{
            this.getChildren().forEach(x-> x.setVisible(false));
            createTeamExistUser.setVisible(true);
            createTeamExistUser.setAlignItems(Alignment.CENTER);
        });

        btn_calculateInlayPolicy.addClickListener(e ->{
            this.getChildren().forEach(x-> x.setVisible(false));
            calculateInlayPolicy.setVisible(true);
            calculateInlayPolicy.setAlignItems(Alignment.CENTER);
        });

        btn_calculatePointsAndPlace.addClickListener(e ->{
            this.getChildren().forEach(x-> x.setVisible(false));
            calculatePointsAndPlace.setVisible(true);
            calculatePointsAndPlace.setAlignItems(Alignment.CENTER);
        });
    }

    private VerticalLayout calculateInlayPolicyLayout(GuestApplication service) {
        TextField sessionName=new TextField("Session Name");
        TextField year=new TextField("League year");
        String activeUserName=(String) VaadinSession.getCurrent().getAttribute("user");
        String[] policyArray= service.getCalculatorPolicy(activeUserName);
        Select<String> policySelect = new Select<>();
        policySelect.setItems(Arrays.stream(policyArray).filter(x->!(x.equalsIgnoreCase("Respond"))));
        policySelect.setLabel("Policy");

        Button btn_set =new Button("Set",buttonClickEvent -> {
            String policySelectValue= policySelect.getValue();
            String[] ans=service.setLegueCalculator(sessionName.getValue(), Integer.parseInt(year.getValue()),policySelectValue ,activeUserName);
            respondPolicy(ans);

        });
        return new VerticalLayout(sessionName,year,policySelect,btn_set);
    }

    private VerticalLayout calculatePointsAndPlaceLayout(GuestApplication service) {
        TextField leagueName=new TextField("League Name");
        TextField year=new TextField("League year");
        String activeUserName=(String) VaadinSession.getCurrent().getAttribute("user");
        String[] policyArray= service.getGamesScedualsPolicy(activeUserName);

        Select<String> schedulePolicySelect = new Select<>();
        schedulePolicySelect.setItems(Arrays.stream(policyArray).filter(x->!(x.equalsIgnoreCase("Respond"))));
        schedulePolicySelect.setLabel("Schedule Policy");
        Button btn_set =new Button("Set",buttonClickEvent -> {
             String policySelectValue = schedulePolicySelect.getValue();
            String[] ans=service.setGamesSceduals(leagueName.getValue(), Integer.parseInt(year.getValue()),policySelectValue,activeUserName);
            respondPolicy(ans);
        });
        return new VerticalLayout(leagueName,year,schedulePolicySelect, btn_set);
    }

    private void respondPolicy(String[] ans) {
        if(ans!=null) {
            if (ans[0].equalsIgnoreCase("respond")) {
                Notification.show("The action was successful");
            } else {
                Notification.show(ans[1]);
            }
        }
        else
            Notification.show("could not add policy");

        this.getChildren().forEach(x-> x.setVisible(false));
        mainIFA.setVisible(true);
    }

    private VerticalLayout createTeamExistUserLayout(GuestApplication service) {
        TextField ownerName=new TextField("Owner Name");
        TextField groupName=new TextField("Group Name");
        Button btn_set =new Button("Set",buttonClickEvent -> {
            String activeUserName=(String) VaadinSession.getCurrent().getAttribute("user");
            String[] ans=service.addTeam(groupName.getValue(),ownerName.getValue(),activeUserName);
            if(ans!=null) {
                if (ans[0].equalsIgnoreCase("respond")) {
                    Notification.show("group added");
                } else {
                    Notification.show(ans[1]);
                }
            }
            else
                Notification.show("could not add a group");

            this.getChildren().forEach(x-> x.setVisible(false));
            mainIFA.setVisible(true);
        });
        return new VerticalLayout(ownerName,groupName, btn_set);

    }

    private VerticalLayout createTeamAddOwnerLayout(GuestApplication service) {
        TextField ownerName=new TextField("Owner Name");
        TextField userName=new TextField("User Name");
        TextField password=new TextField("Password");
        Button btn_set =new Button("Set",buttonClickEvent -> {
            String activeUserName=(String) VaadinSession.getCurrent().getAttribute("user");
            String[] ans=service.addOwner(ownerName.getValue(),userName.getValue(),password.getValue(),activeUserName );
            if(ans!=null) {
                if (ans[0].equalsIgnoreCase("respond")) {
                    Notification.show("Owner added");
                } else {
                    Notification.show(ans[1]);
                }
            }
            else
                Notification.show("could not add a Owner");

            this.getChildren().forEach(x-> x.setVisible(false));
            mainIFA.setVisible(true);
        });
        return new VerticalLayout(ownerName,userName,password, btn_set);
    }
}
