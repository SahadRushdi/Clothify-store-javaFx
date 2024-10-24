package controller.kidsProductAdmin;

import javafx.collections.ObservableList;
import model.KidsProduct;

public interface KidsProductAdminService {
    ObservableList<KidsProduct> getAll();
    KidsProduct searchKidsProducts(String id);
}
