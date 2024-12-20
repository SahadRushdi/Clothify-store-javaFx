package controller.gentsProductAdmin;

import controller.gentsProduct.GentsProductController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.GentsProducts;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GentsProductAdminController implements GentsProductAdminService {

    private static GentsProductAdminController instance;
    public ObservableList getID;

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
            String SQL = "INSERT INTO gentsproducts VALUES (?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
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
    public boolean deleteGentsProduct(String id) {
        try {
            return CrudUtil.execute("DELETE FROM gentsProducts WHERE ID=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateGentsProduct(GentsProducts gentsProducts) {
        String SQL = "UPDATE gentsproducts SET Name=?, Size=?, Quantity=?, Price=?, Supplier=? WHERE ID=?";
        try {
            return CrudUtil.execute(
                    SQL,
                    gentsProducts.getName(),
                    gentsProducts.getSize(),
                    gentsProducts.getQuantity(),
                    gentsProducts.getPrice(),
                    gentsProducts.getSupplier(),
                    gentsProducts.getID()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public ObservableList<String> getName() {
        ObservableList<String> getDressIds = FXCollections.observableArrayList();
        ObservableList<GentsProducts> all = getAll();

        all.forEach(gentsProducts -> {
            getDressIds.add(gentsProducts.getName());
        });

        return getDressIds;
    }

//    public ObservableList<String> getID() {
//        ObservableList<String> IDs = FXCollections.observableArrayList();
//        ObservableList<GentsProducts> gentsProducts = getAll();
//
//        try {
//            String SQL = "SELECT ID FROM gentsproducts";
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement psTm = connection.prepareStatement(SQL);
//            ResultSet resultSet = psTm.executeQuery();
//
//            while (resultSet.next()) {
//                IDs.add(resultSet.getString("ID"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return IDs;
//    }
}
