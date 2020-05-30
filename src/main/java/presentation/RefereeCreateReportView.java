package presentation;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import service.GuestApplication;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "RefreeCreateReport", layout = MainLayout.class)
public class RefereeCreateReportView extends VerticalLayout {

    public RefereeCreateReportView(@Autowired GuestApplication service){
        TextField idGameName=new TextField("Id Game Name");
        Button btn_set =new Button("Set",buttonClickEvent -> {

            if (!isLegalNum(idGameName.getValue())) {
                Notification.show("id need to be number",1000, Notification.Position.MIDDLE);
            } else {
                String activeUserName=(String) VaadinSession.getCurrent().getAttribute("user");
                String[] ans = service.createReport(
                        Integer.parseInt(idGameName.getValue()),activeUserName);
                if (ans != null) {
                    if (ans[0].equalsIgnoreCase("respond")) {
                        Notification.show("The action was successful");
                        UI.getCurrent().navigate("Referee");

                    } else {
                        Notification.show(ans[1],1000, Notification.Position.MIDDLE);
                    }
                } else
                    Notification.show("could not add policy",1000, Notification.Position.MIDDLE);
            }
        });

        add(idGameName);
        add(btn_set);
        setAlignItems(Alignment.CENTER);

        UI.getCurrent();
    }

    private boolean isLegalNum(String value) {
        for (int i=0; i<value.length(); i++){
            char c = value.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


}
