package repositories;

import model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getUser(String code);
}
