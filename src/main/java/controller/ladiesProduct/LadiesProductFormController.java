package controller.ladiesProduct;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GentsProducts;
import model.LadiesProduct;

import java.net.URL;
import java.util.ResourceBundle;

public class LadiesProductFormController implements Initializable {


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
    private TableView<LadiesProduct> tblLadiesProducts;

    LadiesProductService service = LadiesProductController.getInstance();

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTables();
    }

    private void loadTables() {
        ObservableList<LadiesProduct> ladiesProductObservableList = service.getAll();

        tblLadiesProducts.setItems(ladiesProductObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        tblLadiesProducts.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            // TODO: Implement product details view
        }));
        loadTables();
    }
}
