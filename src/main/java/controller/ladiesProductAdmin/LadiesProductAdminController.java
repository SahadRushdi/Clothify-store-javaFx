package controller.ladiesProductAdmin;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LadiesProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LadiesProductAdminController implements LadiesProductAdminService {

    private static LadiesProductAdminController instance;
    private LadiesProductAdminController(){}

    public static LadiesProductAdminController getInstance() {
        return instance == null ? instance = new LadiesProductAdminController() : instance;
    }

    public ObservableList<LadiesProduct> getAll() {
        ObservableList<LadiesProduct> ladiesProductObservableList = FXCollections.observableArrayList();


        try {
            String SQL = "SELECT * FROM ladiesproducts";

            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);

            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()) {
                LadiesProduct ladiesProduct = new LadiesProduct(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Size"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("Supplier")
                );
                ladiesProductObservableList.add(ladiesProduct);
                System.out.println(ladiesProduct);
            }
            return ladiesProductObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LadiesProduct searchLadiesProducts(String id) {
        String SQL = "SELECT * FROM ladiesproducts WHERE ID = '" + id + "'";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()) {
                return new LadiesProduct(
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
