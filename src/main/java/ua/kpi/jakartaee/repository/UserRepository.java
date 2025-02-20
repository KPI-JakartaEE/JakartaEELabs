package ua.kpi.jakartaee.repository;

import ua.kpi.jakartaee.repository.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();
    Optional<User> findByUsername(String username);
}
