package controller.kidsProductAdmin;

import controller.kidsProduct.KidsProductController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.KidsProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KidsProductAdminController implements KidsProductAdminService {

    private static KidsProductAdminController instance;
    private KidsProductAdminController() {}

    public static KidsProductAdminController getInstance() {
        return instance == null? instance = new KidsProductAdminController() : instance;
    }
    @Override
    public ObservableList<KidsProduct> getAll() {
        ObservableList<KidsProduct> kidsProductObservableList = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT * FROM kidsproducts";

            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);

            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()) {
                KidsProduct kidsProduct = new KidsProduct(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Size"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("Supplier")
                );
                kidsProductObservableList.add(kidsProduct);
                System.out.println(kidsProduct);
            }
            return kidsProductObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KidsProduct searchKidsProducts(String id) {
        String SQL = "SELECT * FROM kidsproducts WHERE ID = '" + id + "'";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()) {
                return new KidsProduct(
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
