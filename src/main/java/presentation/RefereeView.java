package presentation;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import service.GuestApplication;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "Referee", layout = MainLayout.class)
public class RefereeView extends VerticalLayout {
    HorizontalLayout mainReferee;

    public RefereeView(@Autowired GuestApplication service){
        Button btn_createEvent=new Button("Create Event");
        Button btn_createReport=new Button("Create Report");
        mainReferee =new HorizontalLayout(btn_createEvent,btn_createReport);
        mainReferee.addClassName("ifa-div");
        mainReferee.setAlignItems(Alignment.CENTER);

        add(mainReferee);


        btn_createEvent.addClickListener(e ->{
            UI.getCurrent().navigate("RefreeCreateEvent");
        });

        btn_createReport.addClickListener(e ->{
            UI.getCurrent().navigate("RefreeCreateReport");
        });

        UI.getCurrent();
    }






}
