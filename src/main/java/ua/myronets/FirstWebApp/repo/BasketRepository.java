package ua.myronets.FirstWebApp.repo;

import org.springframework.data.repository.CrudRepository;
import ua.myronets.FirstWebApp.models.Basket;

public interface BasketRepository extends CrudRepository<Basket, Long> {

}
