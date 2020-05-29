package gui;




import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    public HomeView(@Autowired GuestApplication service){
        add(new H1("Hello World"));

    }
}
