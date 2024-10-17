package controller.product;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.GentsProducts;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class GentsProductController implements Initializable {

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
    private TableView<?> tblGentsProducts;

    @FXML
    void btnReloadOnAction(ActionEvent event) {


        try {
            String SQL = "SELECT * FROM gentsproducts";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothfystore", "root", "12345");
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Supplier"));

                GentsProducts gentsProducts = new GentsProducts(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Size"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("Supplier")
                );
                System.out.println(gentsProducts);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
