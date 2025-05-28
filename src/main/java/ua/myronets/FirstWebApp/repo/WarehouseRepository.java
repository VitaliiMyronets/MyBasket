package ua.myronets.FirstWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.myronets.FirstWebApp.models.User;
import ua.myronets.FirstWebApp.models.Warehouse;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByUser(User user);
}
