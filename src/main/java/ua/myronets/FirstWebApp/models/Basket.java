package ua.myronets.FirstWebApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String basketName, description, creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getBasketName() {
        return basketName;
    }

    public void setBasketName(String fridgeName) {
        this.basketName = fridgeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Basket() {
    }

    public Basket(String basketName, String description, String creator) {
        this.basketName = basketName;
        this.description = description;
        this.creator = creator;
    }
}
