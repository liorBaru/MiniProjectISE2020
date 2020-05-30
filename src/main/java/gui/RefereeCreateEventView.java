package gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;


@Route(value = "RefreeCreateEvent", layout = MainLayout.class)
public class RefereeCreateEventView extends VerticalLayout {

    public RefereeCreateEventView(@Autowired GuestApplication service) {
        this.setAlignItems(Alignment.CENTER);
        TextField playerUserName = new TextField("Player User Name");
        NumberField gameID = new NumberField("Game ID");
        Select<String> eventSelect = new Select<>();
//        Goal, Offside, Foul, RedCard, YellowCard, Injury, ReplacementIn, ReplacementOut
        eventSelect.setItems("Goal", "Offside","Foul","RedCard"
                ,"YellowCard","Injury","ReplacementIn","ReplacementOut");
        eventSelect.setLabel("Event");
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

            String playerUserNameValue=playerUserName.getValue();
            String eventValue=eventSelect.getValue();
            int minuteValue=numField_minute.getValue().intValue();
            int gameIDValue=gameID.getValue().intValue();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate= datePicker.getValue();
            String user=(String) VaadinSession.getCurrent().getAttribute("user");
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            String[] ans=service.addedEvent(playerUserNameValue,gameIDValue,eventValue,minuteValue,date,user);
            if(ans!=null) {
                if (ans[0].equalsIgnoreCase("respond")) {
                    Notification.show("The action was successful",1000, Notification.Position.MIDDLE);
                } else {
                    Notification.show(ans[1],1000, Notification.Position.MIDDLE);
                }
            }
            else
                Notification.show("could not add policy",1000, Notification.Position.MIDDLE);


        });
//
       add(playerUserName,gameID,eventSelect,numField_minute,datePicker,btn_set);
//
   }



}
