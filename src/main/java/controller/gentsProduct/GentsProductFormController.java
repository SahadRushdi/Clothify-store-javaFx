package controller.gentsProduct;

import com.jfoenix.controls.JFXTextField;
import controller.gentsProductAdmin.GentsProductAdminController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.GentsProducts;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class GentsProductFormController implements Initializable {

    public Text lblDate;
    public Text lblTime;
    public ComboBox cmbDressCode;
    public JFXTextField txtName;
    public JFXTextField txtSize;
    public JFXTextField txtPrice;
    public JFXTextField txtQuantity;
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
    private TableView<GentsProducts> tblGentsProducts;

GentsProductService service = GentsProductController.getInstance();

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTables();
    }

    private void loadTables() {
        ObservableList<GentsProducts> gentsProductsObservableList = service.getAll();

        tblGentsProducts.setItems(gentsProductsObservableList);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDateAndTime();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        tblGentsProducts.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            // TODO: Implement product details view
        }));
        loadTables();

    }

    private void loadDressCode() {
        cmbDressCode.setItems(GentsProductAdminController.getInstance().getName());
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
    }
}
