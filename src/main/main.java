package main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.domain.manageUsers.Guest;
import main.service.GuestApplication;

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
        GuestApplication guestApplication = new GuestApplication(guest);


        guest.addObserver(guestApplication);
        guestApplication.addObserver(view);
        view.setController(guestApplication);

        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}
