package controller.gentsProductAdmin;

import javafx.collections.ObservableList;
import model.GentsProducts;

public interface GentsProductAdminService {
    ObservableList<GentsProducts> getAll();
    boolean addGentsProduct(GentsProducts gentsProducts);
    boolean deleteGentsProduct(String id);
    GentsProducts searchGentsProducts(String id);
}
