package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class DashFormController {

    @FXML
    void btnAdminLoginOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/dress_selection_admin.fxml"))));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "ERROR : " + e);
        }
    }

    @FXML
    void btnCustomerLoginOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer_login.fxml"))));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "ERROR : " + e);
        }
    }

}
