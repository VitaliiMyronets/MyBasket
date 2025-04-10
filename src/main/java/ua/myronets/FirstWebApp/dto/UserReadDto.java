package ua.myronets.FirstWebApp.dto;

import lombok.Value;
import ua.myronets.FirstWebApp.models.Role;
import ua.myronets.FirstWebApp.models.Warehouse;

import java.util.List;

@Value
public class UserReadDto {

    Long id;

    String login;

    String password;

    String firstName;

    String lastName;

    Role role;

    List<Warehouse> warehouses;
}
