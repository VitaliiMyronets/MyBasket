package ua.myronets.FirstWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.myronets.FirstWebApp.models.Warehouse;
import ua.myronets.FirstWebApp.repo.WarehouseRepository;

import java.util.List;

@Service
@Transactional
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public Warehouse createNewWarehouse (Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse findWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }


    public List<Warehouse> findAllWarehouse() {
        return warehouseRepository.findAll();
    }


    public Warehouse updateWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }


    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

}
