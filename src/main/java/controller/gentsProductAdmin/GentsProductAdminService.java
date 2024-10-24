package controller.gentsProductAdmin;

import javafx.collections.ObservableList;
import model.GentsProducts;

public interface GentsProductAdminService {
    ObservableList<GentsProducts> getAll();
    boolean addGentsProduct(GentsProducts gentsProducts);
    GentsProducts searchGentsProducts(String id);
}