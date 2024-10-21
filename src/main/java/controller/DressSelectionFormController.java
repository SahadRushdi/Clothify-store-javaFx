package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class DressSelectionFormController {

    @FXML
    void btnGentsDressOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/gents_product.fxml"))));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "ERROR : " + e);
        }
    }

    @FXML
    void btnKidsDressOnAction(ActionEvent event) {

    }

    @FXML
    void btnLadiesDressOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ladies_product.fxml"))));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "ERROR : " + e);
        }
    }

}
