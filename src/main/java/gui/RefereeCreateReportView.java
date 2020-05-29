package gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "RefreeCreateReport", layout = MainLayout.class)
public class RefereeCreateReportView extends VerticalLayout {

    public RefereeCreateReportView(@Autowired GuestApplication service){
        TextField idGameName=new TextField("Id Game Name");
        Button btn_set =new Button("Set",buttonClickEvent -> {

            if (!isLegalNum(idGameName.getValue())) {
                Notification.show("id need to be number");
            } else {
                String[] ans = service.createReport(
                        Integer.parseInt(idGameName.getValue()));
                if (ans != null) {
                    if (ans[0].equalsIgnoreCase("respond")) {
                        Notification.show("The action was successful");
                        UI.getCurrent().navigate("Refree");

                    } else {
                        Notification.show(ans[1]);
                    }
                } else
                    Notification.show("could not add policy");


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
