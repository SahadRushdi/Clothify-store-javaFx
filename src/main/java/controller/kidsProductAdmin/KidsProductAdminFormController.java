package controller.kidsProductAdmin;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.KidsProduct;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class KidsProductAdminFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplier;

    @FXML
    private Text lblDate;

    @FXML
    private Text lblTime;

    @FXML
    private TableView<KidsProduct> tblKidsProductsAdmin;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtSupplier;

    KidsProductAdminService service = KidsProductAdminController.getInstance();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        KidsProduct kidsProduct = new KidsProduct(
                Integer.parseInt(txtId.getText()),
                txtName.getText(),
                txtSize.getText(),
                Integer.parseInt(txtQuantity.getText()),
                Double.parseDouble(txtPrice.getText()),
                txtSupplier.getText()
        );

        if (service.addkidsProducts(kidsProduct)) {
            new Alert(Alert.AlertType.INFORMATION, "Item added successfully").show();
            loadTables();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to add item").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (service.deletekidsProduct(txtId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully").show();
            loadTables();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete item").show();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTables();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        KidsProduct kidsProduct = service.searchKidsProducts(txtId.getText());
        setTextToValue(kidsProduct);

        if (kidsProduct == null) {
            new Alert(Alert.AlertType.INFORMATION, "Item not found").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Item found").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadTables() {
        ObservableList<KidsProduct> kidsProductsObservableList = service.getAll();

        tblKidsProductsAdmin.setItems(kidsProductsObservableList);
    }

    private void loadDateAndTime() {

        //-------------For Date--------
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);

        lblDate.setText(dateNow);

        //-------------For Time--------
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setTextToValue(KidsProduct newValue) {
        txtId.setText(String.valueOf(newValue.getID()));
        txtName.setText(newValue.getName());
        txtPrice.setText(Double.toString(newValue.getPrice()));
        txtQuantity.setText(Integer.toString(newValue.getQuantity()));
        txtSize.setText(newValue.getSize());
        txtSupplier.setText(newValue.getSupplier());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDateAndTime();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        tblKidsProductsAdmin.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValue(newValue);
        }));
        loadTables();
    }
}
