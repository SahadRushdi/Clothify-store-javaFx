package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KidsProduct {
    private Integer ID;
    private String Name;
    private String Size;
    private Integer Quantity;
    private Double Price;
    private String Supplier;
}
