package controller.ladiesProductAdmin;

import javafx.collections.ObservableList;
import model.LadiesProduct;

public interface LadiesProductAdminService {
    ObservableList<LadiesProduct> getAll();
}
