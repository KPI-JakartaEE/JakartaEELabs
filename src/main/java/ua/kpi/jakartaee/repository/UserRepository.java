package ua.kpi.jakartaee.repository;

import ua.kpi.jakartaee.repository.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);
}
