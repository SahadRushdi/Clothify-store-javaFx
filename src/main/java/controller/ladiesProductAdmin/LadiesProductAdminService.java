package controller.ladiesProductAdmin;

import javafx.collections.ObservableList;
import model.LadiesProduct;

public interface LadiesProductAdminService {
    ObservableList<LadiesProduct> getAll();
    boolean addLadiesProduct(LadiesProduct ladiesProduct);
    boolean deleteLadiesProduct(String id);
    LadiesProduct searchLadiesProducts(String id);
}
