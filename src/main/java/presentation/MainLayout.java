package presentation;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import domain.service.GuestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@PWA(name = "IFA",
        shortName = "IFA App",
        description = "This is  university project application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@Component @UIScope
public class MainLayout extends AppLayout {
    private Button btn_login;
    private Button btn_logout;
    private Button btn_message;
    private Button btn_register;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainLayout(@Autowired GuestApplication service) {
        createHeader();

    }
    private void createHeader() {
        H1 logo = new H1("Welcome to Israeli Football Association ");
        logo.addClassName("logo");
        btn_login=new Button("login");
        btn_logout=new Button("logout");
        btn_message=new Button("message");
        btn_register=new Button("register");
        btn_logout.setVisible(false);
        btn_message.setVisible(false);
        btn_message.addClickListener
                (click ->UI.getCurrent().navigate("message"));
        btn_logout.addClickListener(buttonClickEvent -> {
            VaadinSession.getCurrent().setAttribute("user",null);
            btn_logout.setVisible(false);
            btn_login.setVisible(true);
            btn_message.setVisible(false);
            UI.getCurrent().navigate("");
        });
        btn_register.addClickListener(c-> UI.getCurrent().navigate("register"));
        btn_login.addClickListener(buttonClickEvent ->
                UI.getCurrent().navigate("login"));
        HorizontalLayout header = new HorizontalLayout(logo, btn_login,btn_message,btn_logout,btn_register);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        addToNavbar(header);
        if(VaadinSession.getCurrent().getAttribute("user")!=null){
            btn_login.setVisible(false);
            btn_register.setVisible(false);
            btn_logout.setVisible(true);
            btn_message.setVisible(true);
        }
    }
    public Button getBtn_login() {
        return btn_login;
    }

    public Button getBtn_logout() {
        return btn_logout;
    }

    public Button getBtn_message() {
        return btn_message;
    }

    public Button getBtn_register() { return btn_register; }


}
