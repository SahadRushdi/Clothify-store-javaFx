package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class DressSelectionFormAdminController {

    @FXML
    void btnGentsProductsOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/gents_product_admin.fxml"))));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "ERROR : " + e);
        }
    }

    @FXML
    void btnKidsProductsOnAction(ActionEvent event) {

    }

    @FXML
    void btnLadiesProductsOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/ladies_product_admin.fxml"))));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "ERROR : " + e);
        }
    }

}
