package repositories.impl;

import errors.BusinessError;
import io.vavr.control.Either;
import model.AdminUser;
import repositories.AdminRepository;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Optional;

public class AdminRepositoryImpl implements AdminRepository {

    private final static AdminUser ADMIN_USER = new AdminUser("1", false, "0", OffsetDateTime.now());
    private final static AdminUser OLD_ADMIN_USER = new AdminUser("0", true, "1", OffsetDateTime.now());
    private HashMap<String, AdminUser> admin_user_list = new HashMap<String, AdminUser>(){{
        put(ADMIN_USER.getCode(), ADMIN_USER);
        put(OLD_ADMIN_USER.getCode(), OLD_ADMIN_USER);
    }};

    @Override
    public Optional<AdminUser> getUserAdmin(String code){
        return Optional.ofNullable(admin_user_list.get(code));
    }

    @Override
    public Either<BusinessError, Boolean> updateAdmin(AdminUser adminUser) {
        admin_user_list.put(adminUser.getCode(), adminUser);
        return Either.right(true);
    }

    @Override
    public Either<BusinessError, Boolean> insertAdmin(AdminUser adminUser) {
        admin_user_list.put(adminUser.getCode(), adminUser);
        return Either.right(true);
    }

    @Override
    public Either<BusinessError, Boolean> deleteAdmin(AdminUser adminUser) {
        admin_user_list.put(adminUser.getCode(), adminUser);
        return Either.right(true);
    }
}
