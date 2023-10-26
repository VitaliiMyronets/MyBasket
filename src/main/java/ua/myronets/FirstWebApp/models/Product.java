package ua.myronets.FirstWebApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String nameOfProduct, unit;
    float quantity;

    public Product(String nameOfProduct, String unit, float quantity) {
        this.nameOfProduct = nameOfProduct;
        this.unit = unit;
        this.quantity = quantity;
    }
}
