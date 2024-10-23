package controller.gentsProductAdmin;

import controller.gentsProduct.GentsProductController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GentsProducts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GentsProductAdminController implements GentsProductAdminService {

    private static GentsProductAdminController instance;
    private GentsProductAdminController(){}

    public static GentsProductAdminController getInstance() {
        return instance == null ? instance = new GentsProductAdminController() : instance;
    }

    public ObservableList<GentsProducts> getAll() {
        ObservableList<GentsProducts> gentsProductsObservableList = FXCollections.observableArrayList();


        try {
            String SQL = "SELECT * FROM gentsproducts";

            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);

            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()) {
                GentsProducts gentsProducts = new GentsProducts(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Size"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("Supplier")
                );
                gentsProductsObservableList.add(gentsProducts);
                System.out.println(gentsProducts);
            }
            return gentsProductsObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
