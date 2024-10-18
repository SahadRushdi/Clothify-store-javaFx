package controller.product;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GentsProducts;

import java.net.URL;
import java.util.ResourceBundle;

public class GentsProductFormController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}
