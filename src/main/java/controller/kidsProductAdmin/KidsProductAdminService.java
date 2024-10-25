package controller.kidsProductAdmin;

import javafx.collections.ObservableList;
import model.KidsProduct;

public interface KidsProductAdminService {
    ObservableList<KidsProduct> getAll();
    boolean addkidsProducts(KidsProduct kidsProduct);
    boolean deletekidsProduct(String id);
    KidsProduct searchKidsProducts(String id);
}
