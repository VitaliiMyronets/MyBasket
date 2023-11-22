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
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String basketName, description, creator;



    public Basket(String basketName, String description, String creator) {
        this.basketName = basketName;
        this.description = description;
        this.creator = creator;
    }
}
