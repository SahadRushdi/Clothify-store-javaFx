package controller.kidsProduct;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.KidsProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KidsProductController implements KidsProductService {

    private static KidsProductController instance;
    private KidsProductController() {}

    public static KidsProductController getInstance() {
        return instance == null? instance = new KidsProductController() : instance;
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
}
