package ua.myronets.FirstWebApp.security;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.myronets.FirstWebApp.models.Warehouse;
import ua.myronets.FirstWebApp.service.WarehouseService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WarehouseSecurity {
    private final WarehouseService warehouseService;

    public boolean isOwner(Long warehouseId, String username) {
        Optional<Warehouse> warehouseOptional = warehouseService.findWarehouseById(warehouseId);
        return warehouseOptional.map(warehouse ->
                warehouse.getUser() != null && warehouse.getUser().getUsername().equals(username)
        ).orElse(false);
    }

}

