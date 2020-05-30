package presentation;




import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import service.GuestApplication;
import org.springframework.beans.factory.annotation.Autowired;
@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    public HomeView(@Autowired GuestApplication service){

    }
}
