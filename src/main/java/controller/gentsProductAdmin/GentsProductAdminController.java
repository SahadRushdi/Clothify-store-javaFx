package controller.gentsProductAdmin;

import controller.gentsProduct.GentsProductController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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

    @Override
    public boolean addGentsProduct(GentsProducts gentsProducts) {
        try {
            String SQL = "INSERT INTO gentsproducts VALUES (?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
//            psTm.setInt(1, gentsProducts.getId());
            psTm.setInt(1,gentsProducts.getID());
            psTm.setString(2, gentsProducts.getName());
            psTm.setString(3, gentsProducts.getSize());
            psTm.setInt(4, gentsProducts.getQuantity());
            psTm.setDouble(5, gentsProducts.getPrice());
            psTm.setString(6, gentsProducts.getSupplier());

            return psTm.executeUpdate()>0;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error updating : "+e.getMessage()).show();
        }
        return false;
    }

    @Override
    public GentsProducts searchGentsProducts(String id) {
        String SQL = "SELECT * FROM gentsproducts WHERE ID = '" + id + "'";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()) {
                return new GentsProducts(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Size"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("Supplier")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
