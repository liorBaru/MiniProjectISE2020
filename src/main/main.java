package main;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.domain.manageUsers.Guest;
import main.service.BoardManagerController;
import main.service.FanController;
import main.service.GuestController;
import main.service.IFAController;

import java.util.HashMap;
import java.util.Map;

public class main extends Application {
//    public static Stage primaryStage;
    public View view ;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Soccer System");
        FXMLLoader fxmlLoader= new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("home.fxml").openStream());
        primaryStage.setTitle("Registration Form FXML Application");
        Scene scene = new Scene(root, 900, 506);
        scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
        view= fxmlLoader.getController();
        primaryStage.setScene(scene);

        Guest guest= new Guest();
        GuestController guestController = new GuestController(guest);

        Map<String,GuestController> controllers= createControllers(guest);

        guest.addObserver(guestController);
        guestController.addObserver(view);
        view.setController(guestController, controllers);

        primaryStage.show();

    }

    private Map<String, GuestController> createControllers(Guest guest){
        Map<String,GuestController> controllers = new HashMap<>();
        BoardManagerController boardManagerController= new BoardManagerController(guest);
        controllers.put("BoardMember",boardManagerController);
        FanController fanController = new FanController(guest);
        controllers.put("Fan", fanController);
        IFAController IfaController = new IFAController(guest);
        controllers.put("IFA", IfaController);

        return controllers;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
