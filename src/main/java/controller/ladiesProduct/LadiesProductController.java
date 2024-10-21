package controller.ladiesProduct;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LadiesProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LadiesProductController implements LadiesProductService {

    private static LadiesProductController instance;
    private LadiesProductController(){}

    public static LadiesProductController getInstance() {
        return instance == null? instance = new LadiesProductController() : instance;
    }
    @Override
    public ObservableList<LadiesProduct> getAll() {
        ObservableList<LadiesProduct> ladiesProductObservableList = FXCollections.observableArrayList();

        try {
            String SQL  = "SELECT * FROM ladiesproducts";

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
}
