package controller.kidsProduct;

import javafx.collections.ObservableList;
import model.KidsProduct;

public interface KidsProductService {
    ObservableList<KidsProduct> getAll();
}
