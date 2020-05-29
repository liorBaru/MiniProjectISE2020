package gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


@Route(value = "RefreeCreateEvent", layout = MainLayout.class)
public class RefereeCreateEventView extends VerticalLayout {

    public RefereeCreateEventView(@Autowired GuestApplication service) {
        this.setAlignItems(Alignment.CENTER);
        TextField playerUserName = new TextField("Player User Name");
        TextField gameID = new TextField("Game ID");
        TextField event = new TextField("Event");
        NumberField numField_minute = new NumberField("Minute");

        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Date");
        datePicker.setVisible(true);

        Div value = new Div();
        value.setText("Select a value");
        datePicker.addValueChangeListener(e -> {
            if (e.getValue() == null) {
                value.setText("No date selected");
            } else {
                value.setText("Selected date: " + e.getValue());
                // Date date= e.getValue();
            }
        });


     Button btn_set =new Button("Set",buttonClickEvent -> {
//
////            String[] ans=service.addedEvent(
////                    playerUserName.getValue(),gameID.getValue() ,event.getValue(),
////                    minute.getValue(),date.getValue());
////            if(ans!=null) {
////                if (ans[0].equalsIgnoreCase("respond")) {
////                    Notification.show("The action was successful");
////                } else {
////                    Notification.show(ans[1]);
////                }
////            }
////            else
////                Notification.show("could not add policy");
////
////
        });
//
       add(playerUserName,gameID,event,numField_minute,datePicker,btn_set);
//
   }



}
