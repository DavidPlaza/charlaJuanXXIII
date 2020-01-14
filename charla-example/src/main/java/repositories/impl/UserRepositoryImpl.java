package repositories.impl;

import model.User;
import repositories.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final static User USER1 = new User("0", "David");
    private final static User USER2 = new User("1", "Fernando");
    private final static User USER3= new User("2", "Maria");
    private final static List<User> USER_LIST = Arrays.asList(USER1, USER2, USER3);

    public Optional<User> getUser(String code){
        return USER_LIST.stream().filter(user -> code.equals(user.getCode())).findAny();
    }
}