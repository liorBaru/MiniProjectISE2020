package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import main.service.GuestApplication;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private static GuestApplication appController;

    @FXML
    public javafx.scene.control.Button btn_signUp;
    public javafx.scene.control.Button btn_login;
    public javafx.scene.control.Button btn_GroupRegistration;
    public javafx.scene.control.TextField txt_userName;
    public javafx.scene.control.TextField txt_password;
    public javafx.scene.control.TextField txt_name;
    public javafx.scene.control.TextField lab_welcome;
    public javafx.scene.control.TextField txt_teamName;
    public javafx.scene.control.TextField txt_ownerName;

    public void toSignUpPage(ActionEvent actionEvent) throws IOException {
        loadPage(btn_signUp, "signUp.fxml");
    }

    private void loadPage(Control con, String fxml) throws IOException {
        Stage stage = (Stage) con.getScene().getWindow();
        stage.setTitle("Soccer System");
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage.setTitle("Registration Form FXML Application");
        Scene scene = new Scene(root, 900, 506);
        scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    public void signUp(ActionEvent actionEvent) {
        if(txt_name.getText().isEmpty()) {
            beAttentionAlert("please enter name");
            return;
        }
        if(txt_userName.getText().isEmpty()) {
            beAttentionAlert("please enter user name");
            return;
        }
        if(txt_password.getText().isEmpty()) {
            beAttentionAlert("please enter password");
            return;
        }
        try {
            if (appController.register(txt_name.getText(), txt_userName.getText(),txt_password.getText())) {
                congratulationsAlert("You have signed up for the system");
                loadPage(txt_name,"login.fxml");
            }
            else
                beAttentionAlert("Username length should be at least 6 characters\n" +
                        "and password length should be at least 8 characters (digit, capitals and low)");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void login(ActionEvent actionEvent) {
        if(txt_userName.getText().isEmpty()) {
            beAttentionAlert("please enter user name");
            return;
        }
        if(txt_password.getText().isEmpty()) {
            beAttentionAlert("please enter password");
            return;
        }
        try {
            String userType= appController.login(txt_userName.getText(),txt_password.getText());
             if(!userType.equals("invalid")) {
                 congratulationsAlert("welcome "+userType+" "+ txt_userName.getText());
                 loadPage(txt_userName, userType+".fxml");
             }
            else
                beAttentionAlert("Username length should be at least 6 characters\n" +
                        "and password length should be at least 8 characters (digit, capitals and low)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toLoginPage(ActionEvent actionEvent) throws IOException {
        loadPage(btn_login,"login.fxml");
    }


    public void toCreateTeamPage(ActionEvent actionEvent) throws IOException {
        loadPage(btn_GroupRegistration,"createTeam.fxml");
    }

    public void createTeam(ActionEvent actionEvent) throws Exception {
        String ans= appController.openTeam(txt_teamName.getText(),txt_ownerName.getText());
        if(ans.equals("team added")){
            congratulationsAlert("added team");
        }
        else {
            beAttentionAlert(ans);
        }
    }

    private void beAttentionAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Be Attention");
        alert.setHeaderText(message+"!\n");
        alert.showAndWait();
    }
    private void congratulationsAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("congratulations");
        alert.setHeaderText(message+"!\n");
        alert.showAndWait();
    }



    @Override
    public void update(Observable o, Object arg) {

    }

    public void setController(GuestApplication guestApplication){
        appController = guestApplication;
    }


    public void toCalculationPolicy(ActionEvent actionEvent) {
    }

    public void toLeaguePosition(ActionEvent actionEvent) {
    }

    public void toGamePolicyPage(ActionEvent actionEvent) {
    }


}
