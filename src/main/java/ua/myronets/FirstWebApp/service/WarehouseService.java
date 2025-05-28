package ua.myronets.FirstWebApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.myronets.FirstWebApp.models.Warehouse;
import ua.myronets.FirstWebApp.repo.WarehouseRepository;
import ua.myronets.FirstWebApp.models.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WarehouseService {

    @Autowired
    private final WarehouseRepository warehouseRepository;

    public Warehouse createNewWarehouse (Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getWarehousesByUser(User user) {
        return warehouseRepository.findByUser(user);
    }

    public Optional<Warehouse> findWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }


    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }


    public Warehouse updateWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }


    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

}
