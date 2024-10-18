package controller.product;

import javafx.collections.ObservableList;
import model.GentsProducts;

public interface GentsProductService {
    ObservableList<GentsProducts> getAll();
}
