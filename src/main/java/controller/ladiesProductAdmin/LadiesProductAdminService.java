package controller.ladiesProductAdmin;

import javafx.collections.ObservableList;
import model.LadiesProduct;

public interface LadiesProductAdminService {
    ObservableList<LadiesProduct> getAll();
    boolean addLadiesProduct(LadiesProduct ladiesProduct);
    boolean deleteLadiesProduct(String id);
    boolean updateLadiesProduct(LadiesProduct ladiesProduct);
    LadiesProduct searchLadiesProducts(String id);
}
