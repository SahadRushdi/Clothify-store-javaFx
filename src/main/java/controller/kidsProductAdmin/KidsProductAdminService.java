package controller.kidsProductAdmin;

import javafx.collections.ObservableList;
import model.KidsProduct;

public interface KidsProductAdminService {
    ObservableList<KidsProduct> getAll();
    boolean addkidsProducts(KidsProduct kidsProduct);
    boolean deletekidsProduct(String id);
    boolean updateKidsProduct(KidsProduct kidsProduct);
    KidsProduct searchKidsProducts(String id);
}
