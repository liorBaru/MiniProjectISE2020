package presentation;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import domain.service.GuestApplication;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "register", layout = MainLayout.class)
public class RegisterView extends VerticalLayout  {

    public RegisterView(@Autowired GuestApplication service, @Autowired MainLayout mainLayout) {
        FormLayout loginForm=new FormLayout();
        TextField name=new TextField("Name");
        TextField userName=new TextField("Username");
        PasswordField passwordField=new PasswordField("Password");
        Button btn_signIn=new Button("Sign in");
        loginForm.add(name,userName,passwordField,btn_signIn);
        add(loginForm);
        addClassName("centered-content");
        btn_signIn.addClickListener(c-> {
         String valueName,valueUserName,valuePassword;
         valueName=name.getValue();
         valueUserName=userName.getValue();
         valuePassword=passwordField.getValue();
         String[] user=service.register(valueName,valueUserName,valuePassword);

            if(user!=null &&user[0].equalsIgnoreCase("Respond")){
                Notification.show(user[1],10000, Notification.Position.MIDDLE);
            }
            else if(user!=null&& user[0].equalsIgnoreCase("Fail")){
             Notification.show(user[1],5000, Notification.Position.MIDDLE);
         }
            else {
                Notification.show("ERROR",10000, Notification.Position.MIDDLE);
            }
        });
    }
}
